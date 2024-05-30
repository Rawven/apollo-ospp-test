package www.raven.ospp.metrics.reporter.internal;

import www.raven.ospp.metrics.reporter.MetricsReporter;
import www.raven.ospp.metrics.reporter.MetricsReporterFactory;

public class DefaultMetricsReporterFactory implements MetricsReporterFactory {
    @Override
    public MetricsReporter createReporter(String url) {
        return new NopMetricReporter(url);
    }

}
