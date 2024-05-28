package www.raven.ospp.metrics.model;

import www.raven.ospp.metrics.util.MeterType;

public class CounterMetricsSample extends MetricsSample{
    private Double value;

    public CounterMetricsSample(String name,long num) {
        setName(name);
        setValue((double) num);
        setType(MeterType.COUNTER);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
