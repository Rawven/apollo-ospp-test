package www.raven.ospp.metrics.model;

import com.google.common.util.concurrent.AtomicDouble;
import www.raven.ospp.metrics.util.MeterType;

public class CounterMetricsSample extends MetricsSample{
    private AtomicDouble value;

    public CounterMetricsSample(String name,double num) {
        this.name = name;
        this.value = new AtomicDouble(num);
        this.type = MeterType.COUNTER;
    }

    public Double getValue() {
        return value.get();
    }

    public void setValue(Double value) {
        this.value.set(value);
    }
}
