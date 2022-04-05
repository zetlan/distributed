package net.zetlan.examples.client;

import com.fasterxml.jackson.core.type.TypeReference;
import net.zetlan.examples.api.ProductView;
import net.zetlan.examples.cli.ServiceName;
import net.zetlan.examples.config.DistributedExampleConfiguration;
import org.apache.http.client.HttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Singleton
public class ProductClient extends BaseClient{

    @Inject
    public ProductClient(HttpClient httpClient, DistributedExampleConfiguration configuration) {
        super(httpClient, configuration.getServiceConfiguration(ServiceName.PRODUCT));
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
