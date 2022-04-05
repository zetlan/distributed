package net.zetlan.examples.client;

import com.fasterxml.jackson.core.type.TypeReference;
import net.zetlan.examples.api.CartRequests;
import net.zetlan.examples.api.CartView;
import net.zetlan.examples.cli.ServiceName;
import net.zetlan.examples.config.DistributedExampleConfiguration;
import org.apache.http.client.HttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Singleton
public class CartClient extends BaseClient {
    @Inject
    public CartClient(HttpClient httpClient, DistributedExampleConfiguration configuration) {
        super(httpClient, configuration.getServiceConfiguration(ServiceName.CART));
    }

    public CartView getOrCreateForUser(Integer userId) {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/cart")
                .path("for-user")
                .path(userId.toString())
                .build();

        return get(endpoint, new TypeReference<>(){});
    }

    public CartView changeCart(Integer userId, CartRequests.ChangeItems request) {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/cart")
                .path("for-user")
                .path(userId.toString())
                .build();

        return put(endpoint, request, new TypeReference<>(){});
    }

    public CartView clearCart(Integer userId) {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/cart")
                .path("for-user")
                .path(userId.toString())
                .build();

        return delete(endpoint, new TypeReference<>(){});
    }

}
