package www.raven.ospp.metrics;

import lombok.extern.slf4j.Slf4j;
import www.raven.ospp.metrics.collector.internal.MetricsCollector;
import www.raven.ospp.metrics.collector.internal.MetricsCollectorManager;
import www.raven.ospp.metrics.reporter.MetricsReporterManager;
import www.raven.ospp.metrics.util.SimpleInjector;
@Slf4j
public abstract class MetricsCollectHelper {

    private static MetricsCollectorManager collectorManager;
    private static MetricsReporterManager reporterManager;
    static {
        collectorManager = SimpleInjector.getInstance(MetricsCollectorManager.class);
        reporterManager = SimpleInjector.getInstance(MetricsReporterManager.class);
    }


    public static void pushMetricsEvent(MetricsEvent<?> event) {
        for (MetricsCollector collector : collectorManager.getCollectors()) {
            if (collector.isSupport(event.getTag())) {
                collector.collect(event);
                return;
            }
        }
        log.warn("No collector support key: {}", event.getTag());
    }


    public static String getMetricsExportData() {
        return reporterManager.getMetricsReporter().response();
    }



}
