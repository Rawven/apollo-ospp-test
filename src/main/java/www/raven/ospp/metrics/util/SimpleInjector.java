package www.raven.ospp.metrics.util;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import www.raven.ospp.metrics.collector.internal.DefaultMetricsCollectorManager;
import www.raven.ospp.metrics.collector.internal.MetricsCollectorManager;

@Slf4j
public class SimpleInjector {
    private static final Injector injector;
    static {
        injector = Guice.createInjector(new DefaultModule());
    }

    public static <T> T getInstance(Class<T> clazz) {
        try {
            return injector.getInstance(clazz);
        } catch (Throwable ex) {
            log.error("Unable to load instance for type {}!", clazz.getName(), ex);
            throw new RuntimeException(
                String.format("Unable to load instance for type %s!", clazz.getName()), ex);
        }
    }

    public static class DefaultModule extends AbstractModule {
        @Override
        protected void configure() {
             bind(MetricsCollectorManager.class).to(DefaultMetricsCollectorManager.class).in(Singleton.class);
        }
    }
}
