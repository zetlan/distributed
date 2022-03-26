package net.zetlan.examples;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

import javax.inject.Singleton;

public class DistributedExampleModule extends AbstractModule {
    private final HibernateBundle<DistributedExampleConfiguration> hibernateBundle;

    public DistributedExampleModule(HibernateBundle<DistributedExampleConfiguration> hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Provides
    @Singleton
    public SessionFactory provideSessionFactory() {
        return hibernateBundle.getSessionFactory();
    }
}
