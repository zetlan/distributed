package net.zetlan.examples.client;

import com.fasterxml.jackson.core.type.TypeReference;
import net.zetlan.examples.api.UserView;
import net.zetlan.examples.cli.ServiceName;
import net.zetlan.examples.config.DistributedExampleConfiguration;
import org.apache.http.client.HttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Singleton
public class UserClient extends BaseClient{

    @Inject
    public UserClient(HttpClient httpClient, DistributedExampleConfiguration configuration) {
        super(httpClient, configuration.getServiceConfiguration(ServiceName.USER));
    }

    public List<UserView> getUsers() {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/user")
                .build();

        return get(endpoint, new TypeReference<>(){});
    }

    public UserView getByName(String name) {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/user")
                .path(name)
                .build();

        return get(endpoint, new TypeReference<>(){});
    }

    public UserView getById(Integer userId) {
        URI endpoint = UriBuilder.fromUri(baseUri)
                .path("/user")
                .path("id")
                .path(userId.toString())
                .build();

        return get(endpoint, new TypeReference<>(){});
    }
}
