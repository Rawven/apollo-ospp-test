package www.raven.ospp.metrics.collector.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.ToDoubleFunction;
import lombok.extern.slf4j.Slf4j;
import www.raven.ospp.metrics.MBean.TestSample;
import www.raven.ospp.metrics.MBean.TestSampleMBean;
import www.raven.ospp.metrics.MetricsEvent;
import www.raven.ospp.metrics.collector.MetricsCollector;
import www.raven.ospp.metrics.model.CounterMetricsSample;
import www.raven.ospp.metrics.model.GaugeMetricsSample;
import www.raven.ospp.metrics.model.MetricsSample;

@Slf4j
public class TestCollector implements MetricsCollector {
    public static final String NUM_NAME = "TestCounter";
    public static final String TIME_NAME = "TestGauge";
    public static final String STRING_NAME = "TestString";
    private final TestSampleMBean testSampleMBean = new TestSample();
    private final AtomicBoolean isSamplesUpdated = new AtomicBoolean(false);

    public  double convertLongToDouble(long value) {
        return (double) value;
    }

    @Override
    public boolean isSupport(String tag) {
        return Objects.equals(tag, NUM_NAME) || Objects.equals(tag, TIME_NAME) || Objects.equals(tag, STRING_NAME);
    }

    @Override
    public void collect(MetricsEvent event) {
        log.info("TestCollector collect");
        switch (event.getName()) {
            case NUM_NAME:
                testSampleMBean.incNum();
                break;
            case STRING_NAME:
                testSampleMBean.setString1((String) event.getData());
                break;
            case TIME_NAME:
                testSampleMBean.setTime((long) event.getData());
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
        List<MetricsSample> samples = new ArrayList<>();
        samples.add(new CounterMetricsSample(NUM_NAME, testSampleMBean.getNum()));
        samples.add(new GaugeMetricsSample<>(TIME_NAME, testSampleMBean.getTime(), this::convertLongToDouble));
        HashMap<String, String> tag = new HashMap<>(3);
        tag.put("tag1", testSampleMBean.getString1());
        samples.add(new GaugeMetricsSample<>(STRING_NAME, 1, value -> 1, tag));
        return samples;
    }
}
