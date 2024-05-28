package www.raven.ospp.metrics.reporter.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import www.raven.ospp.metrics.reporter.MetricsReporter;
import www.raven.ospp.metrics.reporter.MetricsReporterManager;
import www.raven.ospp.metrics.util.ClassLoaderUtil;

public class DefaultMetricsReporterManager implements MetricsReporterManager {
    private static final Logger log = LoggerFactory.getLogger(DefaultMetricsReporterManager.class);
    private MetricsReporter reporter;

    public DefaultMetricsReporterManager() {
        log.info("DefaultMetricsReporterManager init");
         if(ClassLoaderUtil.isClassPresent("io.prometheus.client.CollectorRegistry")) {
             log.info("PrometheusMetricReporter init");
            reporter = new PrometheusMetricReporter("");
        }else {
             reporter = new NopMetricReporter("");
        }
    }
    @Override
    public MetricsReporter getMetricsReporter() {
        return reporter;
    }
}
