package net.zetlan.examples.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import io.dropwizard.jackson.Jackson;
import net.zetlan.examples.config.ServiceConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public abstract class BaseClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class);

    protected final HttpClient httpClient;

    protected final ServiceConfiguration serviceConfiguration;

    protected final URI baseUri;

    protected final ObjectMapper objectMapper;

    @Inject
    public BaseClient(final HttpClient httpClient, final ServiceConfiguration serviceConfiguration) {
        this.httpClient = Preconditions.checkNotNull(httpClient, "HttpClient may not be null");
        this.serviceConfiguration = Preconditions.checkNotNull(serviceConfiguration, "ServiceConfiguration may not be null");

        this.baseUri = UriBuilder.fromUri(serviceConfiguration.getBaseUri()).build();
        this.objectMapper = Jackson.newObjectMapper();
    }

    protected <V> V get(URI uri, TypeReference<V> responseType) {
        HttpGet httpGet = new HttpGet(uri);
        return executeRequest(httpGet, responseType);
    }

    protected <V, B> V post(URI uri, B bodyObject, TypeReference<V> responseType) {
        HttpPost httpPost = new HttpPost(uri);
        httpPost = setEntityFromObject(httpPost, bodyObject);
        return executeRequest(httpPost, responseType);
    }

    protected <V, B> V put(URI uri, B bodyObject, TypeReference<V> responseType) {
        HttpPut httpPut = new HttpPut(uri);
        httpPut = setEntityFromObject(httpPut, bodyObject);
        return executeRequest(httpPut, responseType);
    }

    protected <V> V delete(URI uri, TypeReference<V> responseType) {
        HttpDelete httpDelete = new HttpDelete(uri);
        return executeRequest(httpDelete, responseType);
    }

    private <R extends HttpEntityEnclosingRequest, B> R setEntityFromObject(R request, B bodyObject) {
        if (bodyObject == null) {
            return request;
        }
        try {
            String bodyJson = this.objectMapper.writeValueAsString(bodyObject);
            StringEntity requestEntity = new StringEntity(bodyJson, ContentType.APPLICATION_JSON);
            request.setEntity(requestEntity);
            return request;
        } catch (JsonProcessingException e) {
            LOGGER.error("JSON Processing error occurred: ", e);
            throw new WebApplicationException("JSON error", e);
        }
    }

    private <V, B> V executeRequest(HttpUriRequest httpUriRequest, TypeReference<V> responseType) {
        try {
            HttpResponse response = httpClient.execute(httpUriRequest);
            HttpEntity httpEntity = response.getEntity();

            byte[] entityBytes = httpEntity.getContent().readAllBytes();
            String entityString = new String(entityBytes, StandardCharsets.UTF_8);

            EntityUtils.consume(httpEntity);
            return this.objectMapper.readValue(entityString, responseType);
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.error("Http error occurred: ", e);
            throw new WebApplicationException("HTTP error", e);
        }
    }
}
