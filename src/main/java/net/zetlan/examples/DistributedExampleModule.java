package net.zetlan.examples;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.setup.Environment;
import net.zetlan.examples.cli.ServiceName;
import net.zetlan.examples.config.DistributedExampleConfiguration;
import org.apache.http.client.HttpClient;

public class DistributedExampleModule extends AbstractModule {
//    private final HibernateBundle<DistributedExampleConfiguration> hibernateBundle;

    public DistributedExampleModule() {
//        this.hibernateBundle = hibernateBundle;
    }

//    @Provides
//    @Singleton
//    public SessionFactory provideSessionFactory() {
//        return hibernateBundle.getSessionFactory();
//    }

    @Provides
    @Singleton
    @Inject
    public HttpClient provideHttpClient(final Environment environment, DistributedExampleConfiguration configuration) {
        HttpClientConfiguration httpClientConfiguration = configuration.getHttpClient();
        ServiceName serviceName = configuration.getServiceName();

        return new HttpClientBuilder(environment).using(httpClientConfiguration)
                .build(serviceName.toString() + "-http-client");
    }
}
