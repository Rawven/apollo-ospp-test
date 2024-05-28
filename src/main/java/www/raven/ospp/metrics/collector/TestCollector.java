package www.raven.ospp.metrics.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import www.raven.ospp.metrics.MBean.TestSample;
import www.raven.ospp.metrics.MBean.TestSampleMBean;
import www.raven.ospp.metrics.MetricsEvent;
import www.raven.ospp.metrics.model.CounterMetricsSample;
import www.raven.ospp.metrics.model.GaugeMetricsSample;
import www.raven.ospp.metrics.model.MetricsSample;
import www.raven.ospp.metrics.util.UtilInjector;
@Slf4j
public class TestCollector implements MetricsCollector {
    private TestSampleMBean testSampleMBean = new TestSample();
    private boolean isSamplesUpdated = false;

    @Override
    public boolean isSupport(String tag) {
        return Objects.equals(tag, "test") || Objects.equals(tag, "test2");
    }

    @Override
    public void collect(MetricsEvent event) {
        log.info("TestCollector collect");
        switch (event.getTag()) {
            case "test":
                testSampleMBean.setNum(testSampleMBean.getNum() + 1);
                break;
            case "test2":
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
        samples.add(new CounterMetricsSample("test",testSampleMBean.getNum()));
        samples.add(new GaugeMetricsSample<>("test2", testSampleMBean.getTime(), Double::valueOf));
        return samples;
    }
}
