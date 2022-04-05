package net.zetlan.examples.client;

import com.fasterxml.jackson.core.type.TypeReference;
import net.zetlan.examples.api.OrderRequests;
import net.zetlan.examples.api.OrderView;
import net.zetlan.examples.cli.ServiceName;
import net.zetlan.examples.config.DistributedExampleConfiguration;
import org.apache.http.client.HttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Singleton
public class OrderClient extends BaseClient {

    @Inject
    public OrderClient(HttpClient httpClient, DistributedExampleConfiguration configuration) {
        super(httpClient, configuration.getServiceConfiguration(ServiceName.ORDER));
    }

    public OrderView placeOrder(OrderRequests.PlaceOrder request) {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/order")
                .build();

        return post(endpoint, request, new TypeReference<>(){});
    }

    public OrderView getById(Integer orderId) {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/order")
                .path(orderId.toString())
                .build();

        return get(endpoint, new TypeReference<>(){});
    }
}
