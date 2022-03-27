package net.zetlan.examples;

import com.google.common.annotations.VisibleForTesting;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.zetlan.examples.cli.ServiceName;
import net.zetlan.examples.config.DistributedExampleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyarus.dropwizard.guice.GuiceBundle;

import javax.persistence.Entity;
import java.util.List;

public class DistributedExampleApplication extends Application<DistributedExampleConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedExampleApplication.class);

    private ServiceName name = ServiceName.TEST;

//    private final HibernateBundle<DistributedExampleConfiguration> hibernate = new HibernateBundle<DistributedExampleConfiguration>(getEntityClasses(), new SessionFactoryFactory()) {
//        @Override
//        public PooledDataSourceFactory getDataSourceFactory(DistributedExampleConfiguration configuration) {
//            return configuration.getDatabase();
//        }
//    };

    private final GuiceBundle guiceBundle = GuiceBundle.builder()
            .enableAutoConfig("net.zetlan.examples")
            .modules(new DistributedExampleModule())
            .build();

    public static void main(final String[] args) throws Exception {
        new DistributedExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return this.name.toString();
    }

    @Override
    public void initialize(final Bootstrap<DistributedExampleConfiguration> bootstrap) {
        LOGGER.error("Initialize");
//        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final DistributedExampleConfiguration configuration,
                    final Environment environment) {
        LOGGER.error("Run");
        this.name = configuration.getServiceName();
        LOGGER.info("Running {} service....", this.name);
    }

    @VisibleForTesting
    static List<Class<?>> getEntityClasses() {
        return Utils.getPackageClassesWithAnnotation("net.zetlan.examples.db", Entity.class);
    }
}
