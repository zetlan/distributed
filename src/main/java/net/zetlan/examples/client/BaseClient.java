package net.zetlan.examples.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import io.dropwizard.jackson.Jackson;
import net.zetlan.examples.config.ServiceConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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

    protected <V> V get(URI uri, TypeReference<V> typeReference) {
        HttpGet httpGet = new HttpGet(uri);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();

            byte[] entityBytes = httpEntity.getContent().readAllBytes();
            String entityString = new String(entityBytes, StandardCharsets.UTF_8);

            EntityUtils.consume(httpEntity);
            return this.objectMapper.readValue(entityString, typeReference);
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.error("Http error occurred: ", e);
            throw new WebApplicationException("HTTP error", e);
        }
    }
}
