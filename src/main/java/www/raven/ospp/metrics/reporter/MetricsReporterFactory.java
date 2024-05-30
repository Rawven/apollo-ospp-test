package www.raven.ospp.metrics.reporter;

public interface  MetricsReporterFactory {
    MetricsReporter createReporter(String url);
    MetricsReporter createReporter();
}
