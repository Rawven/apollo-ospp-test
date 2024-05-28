package www.raven.ospp.metrics.reporter.internal;

import www.raven.ospp.metrics.model.CounterMetricsSample;
import www.raven.ospp.metrics.model.GaugeMetricsSample;
import www.raven.ospp.metrics.reporter.AbstractMetricsReporter;
import www.raven.ospp.metrics.reporter.MetricsReporter;

public class NopMetricReporter extends AbstractMetricsReporter implements MetricsReporter {

    public NopMetricReporter(String url) {
        super(url);
    }

    @Override
    protected void doInit() {

    }

    @Override
    public void registerCounterSample(CounterMetricsSample sample) {

    }

    @Override
    public void registerGaugeSample(GaugeMetricsSample sample) {

    }

    @Override
    public String response() {
        return "";
    }
}
