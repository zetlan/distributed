package net.zetlan.examples.client;

import com.fasterxml.jackson.core.type.TypeReference;
import net.zetlan.examples.api.ProductView;
import net.zetlan.examples.config.ServiceConfiguration;
import org.apache.http.client.HttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Singleton
public class ProductClient extends BaseClient{

    @Inject
    public ProductClient(HttpClient httpClient, ServiceConfiguration serviceConfiguration) {
        super(httpClient, serviceConfiguration);
    }

    public List<ProductView> getProductCatalog() {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/product")
                .build();

        return get(endpoint, new TypeReference<>(){});
    }

    public List<ProductView> getBySku(final String sku) {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/product")
                .path("/sku")
                .path(sku)
                .build();

        return get(endpoint, new TypeReference<>(){});
    }
}
