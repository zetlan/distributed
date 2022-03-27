package net.zetlan.examples.config;

import net.zetlan.examples.cli.ServiceName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ServiceConfiguration {
    @NotNull
    private ServiceName serviceName;
    @NotNull
    @NotEmpty
    private String baseUri;

    public ServiceName getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }
}
