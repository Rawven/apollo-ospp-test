package www.raven.ospp.metrics.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;
import www.raven.ospp.metrics.MBean.TestSample;
import www.raven.ospp.metrics.MBean.TestSampleMBean;
import www.raven.ospp.metrics.MetricsEvent;
import www.raven.ospp.metrics.collector.internal.MetricsCollector;
import www.raven.ospp.metrics.model.CounterMetricsSample;
import www.raven.ospp.metrics.model.GaugeMetricsSample;
import www.raven.ospp.metrics.model.MetricsSample;

@Slf4j
public class TestCollector implements MetricsCollector {
    public static final String NUM_NAME = "TestCounter";
    public static final String TIME_NAME = "TestGauge";
    private final TestSampleMBean testSampleMBean = new TestSample();
    private final AtomicBoolean isSamplesUpdated = new AtomicBoolean(false);

    @Override
    public boolean isSupport(String tag) {
        return Objects.equals(tag, NUM_NAME) || Objects.equals(tag, TIME_NAME);
    }

    @Override
    public void collect(MetricsEvent event) {
        log.info("TestCollector collect");
        switch (event.getTag()) {
            case NUM_NAME:
                testSampleMBean.incNum();
                break;
            case TIME_NAME:
                testSampleMBean.setTime((long)event.getObject());
                break;
            default:
                return;
        }
        isSamplesUpdated.set(true);
    }

    @Override
    public boolean isSamplesUpdated() {
        return isSamplesUpdated.getAndSet(false);
    }

    @Override
    public List<MetricsSample> export() {
        log.info(testSampleMBean.toString());
        List<MetricsSample> samples = new ArrayList<>();
        samples.add(new CounterMetricsSample(NUM_NAME,testSampleMBean.getNum()));
        samples.add(new GaugeMetricsSample<>(TIME_NAME, testSampleMBean.getTime(), TestCollector::convertLongToDouble));
        return samples;
    }

    public static double convertLongToDouble(long value) {
        return (double) value;
    }
}
