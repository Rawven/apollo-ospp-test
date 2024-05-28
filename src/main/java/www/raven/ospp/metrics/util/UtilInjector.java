package www.raven.ospp.metrics.util;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import www.raven.ospp.metrics.MBean.MetricsDataManager;
import www.raven.ospp.metrics.MBean.MetricsDataManagerMBean;
import www.raven.ospp.metrics.MBean.TestSample;
import www.raven.ospp.metrics.MBean.TestSampleMBean;
import www.raven.ospp.metrics.collector.DefaultMetricsCollectorManager;
import www.raven.ospp.metrics.collector.MetricsCollectorManager;
import www.raven.ospp.metrics.reporter.MetricsReporterManager;
import www.raven.ospp.metrics.reporter.internal.DefaultMetricsReporterManager;

@Slf4j
public class UtilInjector {
    private static Injector injector;
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
             bind(MetricsReporterManager.class).to(DefaultMetricsReporterManager.class).in(Singleton.class);
             bind(TestSampleMBean.class).to(TestSample.class).in(Singleton.class);
             bind(MetricsDataManagerMBean.class).to(MetricsDataManager.class).in(Singleton.class);
        }
    }
}
