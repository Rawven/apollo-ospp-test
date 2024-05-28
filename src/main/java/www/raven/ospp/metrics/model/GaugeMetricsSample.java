package www.raven.ospp.metrics.model;

import java.util.function.ToDoubleFunction;

import static www.raven.ospp.metrics.util.MeterType.GAUGE;

public class GaugeMetricsSample<T> extends MetricsSample{
    private T value;

    private ToDoubleFunction<T> apply;

    public GaugeMetricsSample(String name, T value, ToDoubleFunction<T> apply) {
        this.value = value;
        this.apply = apply;
        this.setType(GAUGE);
    }
    public GaugeMetricsSample() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ToDoubleFunction<T> getApply() {
        return this.apply;
    }

    public double getApplyValue() {
        return getApply().applyAsDouble(getValue());
    }
}
