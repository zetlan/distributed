package net.zetlan.examples.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.client.JerseyClientConfiguration;
import net.zetlan.examples.cli.ServiceName;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DistributedExampleConfiguration extends Configuration {

    @NotNull
    private ServiceName serviceName;

    @Valid
    @NotNull
    private JerseyClientConfiguration jersey = new JerseyClientConfiguration();

    private List<ServiceConfiguration> services = new ArrayList<>();

    @Valid
    @NotNull
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

//    @Valid
//    @NotNull
//    private DataSourceFactory database = new DataSourceFactory();

    public ServiceName getServiceName() {
        return serviceName;
    }

//    public DataSourceFactory getDatabase() {
//        return database;
//    }


    public JerseyClientConfiguration getJersey() {
        return jersey;
    }

    public List<ServiceConfiguration> getServices() {
        return services;
    }
    @JsonIgnore
    public ServiceConfiguration getServiceConfiguration(ServiceName name) {
        return this.services
                .stream()
                .filter(config -> name.equals(config.getServiceName()))
                .findFirst()
                .orElse(null);
    }

    public HttpClientConfiguration getHttpClient() {
        return httpClient;
    }
}
