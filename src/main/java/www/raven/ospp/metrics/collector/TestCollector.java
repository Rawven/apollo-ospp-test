package www.raven.ospp.metrics.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private TestSampleMBean testSampleMBean = new TestSample();
    private boolean isSamplesUpdated = false;

    @Override
    public boolean isSupport(String tag) {
        return Objects.equals(tag, NUM_NAME) || Objects.equals(tag, TIME_NAME);
    }

    @Override
    public void collect(MetricsEvent event) {
        log.info("TestCollector collect");
        switch (event.getTag()) {
            case NUM_NAME:
                testSampleMBean.setNum(testSampleMBean.getNum() + 1);
                break;
            case TIME_NAME:
                testSampleMBean.setTime((long)event.getObject());
                break;
            default:
                break;
        }
        isSamplesUpdated = true;
    }

    @Override
    public boolean isSamplesUpdated() {
        return isSamplesUpdated;
    }

    @Override
    public List<MetricsSample> export() {
        List<MetricsSample> samples = new ArrayList<>();
        samples.add(new CounterMetricsSample(NUM_NAME,testSampleMBean.getNum()));
        samples.add(new GaugeMetricsSample<>(TIME_NAME, testSampleMBean.getTime(), Double::valueOf));
        return samples;
    }
}
