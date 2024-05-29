package www.raven.ospp.metrics;

import lombok.extern.slf4j.Slf4j;
import www.raven.ospp.metrics.collector.MetricsCollector;
import www.raven.ospp.metrics.collector.MetricsCollectorManager;
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

    public static <T>void pushMetricsObject(T object, String key) {
        log.info("pushMetricsObject: object={}, key={}", object, key);
        for (MetricsCollector collector : collectorManager.getCollectors()) {
            if (collector.isSupport(key)) {
                collector.collect(MetricsEvent.builder().withObject(object).withTag(key).build());
                return;
            }
        }
        log.warn("No collector support key: {}", key);
    }

    public static String getMetricsExportData() {
        return reporterManager.getMetricsReporter().response();
    }



}
