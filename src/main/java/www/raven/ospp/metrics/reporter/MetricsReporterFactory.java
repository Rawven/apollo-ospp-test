package www.raven.ospp.metrics.reporter;

public interface  MetricsReporterFactory {
    /**
     * create reporter
     *
     * @param url url
     * @return {@link MetricsReporter }
     */
    MetricsReporter createReporter(String url);
}
