package net.zetlan.examples;

import io.dropwizard.Configuration;
import net.zetlan.examples.cli.ServiceName;

import javax.validation.constraints.NotNull;

public class DistributedExampleConfiguration extends Configuration {

    @NotNull
    private ServiceName serviceName;

//    @Valid
//    @NotNull
//    private DataSourceFactory database = new DataSourceFactory();

    public ServiceName getServiceName() {
        return serviceName;
    }

//    public DataSourceFactory getDatabase() {
//        return database;
//    }
}
