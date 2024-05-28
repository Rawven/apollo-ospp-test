package www.raven.ospp.metrics;

import lombok.extern.slf4j.Slf4j;
import www.raven.ospp.metrics.MBean.MetricsDataManagerMBean;
import www.raven.ospp.metrics.collector.MetricsCollector;
import www.raven.ospp.metrics.collector.MetricsCollectorManager;
import www.raven.ospp.metrics.reporter.MetricsReporterManager;
import www.raven.ospp.metrics.util.UtilInjector;
@Slf4j
public abstract class MetricsCollectHelper {

    private static MetricsCollectorManager collectorManager;
    private static MetricsReporterManager reporterManager;
    private static MetricsDataManagerMBean metricsDataManager;
    static {
        collectorManager = UtilInjector.getInstance(MetricsCollectorManager.class);
        reporterManager = UtilInjector.getInstance(MetricsReporterManager.class);
        metricsDataManager = UtilInjector.getInstance(MetricsDataManagerMBean.class);
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

    public static MetricsDataManagerMBean getMetricsDataManager() {
        return metricsDataManager;
    }


}
