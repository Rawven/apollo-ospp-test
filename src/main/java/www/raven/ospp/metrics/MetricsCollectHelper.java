package www.raven.ospp.metrics;

import lombok.extern.slf4j.Slf4j;
import www.raven.ospp.metrics.collector.internal.MetricsCollector;
import www.raven.ospp.metrics.collector.internal.MetricsCollectorManager;
import www.raven.ospp.metrics.reporter.MetricsReporter;
import www.raven.ospp.metrics.reporter.MetricsReporterFactory;
import www.raven.ospp.metrics.reporter.internal.DefaultMetricsReporterFactory;
import www.raven.ospp.metrics.reporter.internal.PrometheusMetricsReporterFactory;
import www.raven.ospp.metrics.util.SimpleInjector;
@Slf4j
public abstract class MetricsCollectHelper {
    public static final String PROMETHEUS = "prometheus";

    private static MetricsCollectorManager collectorManager;
    private static MetricsReporter reporter;

    public static void pushMetricsEvent(MetricsEvent event) {
        for (MetricsCollector collector : collectorManager.getCollectors()) {
            if (collector.isSupport(event.getTag())) {
                collector.collect(event);
                return;
            }
        }
        log.warn("No collector support key: {}", event.getTag());
    }


    public static String getMetricsExportData() {
        return reporter.response();
    }

    public static void simpleInit(String url, String type) {

        //inject the collector manager
        collectorManager = SimpleInjector.getInstance(MetricsCollectorManager.class);

        //inject the reporter
        MetricsReporterFactory factory;
        if(type.equals(PROMETHEUS)) {
            factory = new PrometheusMetricsReporterFactory();
        } else {
            factory = new DefaultMetricsReporterFactory();
        }
        reporter = factory.createReporter(url);
    }



}
