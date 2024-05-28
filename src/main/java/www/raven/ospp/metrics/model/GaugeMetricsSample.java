package com.ctrip.framework.apollo.metrics.model;

import java.util.function.ToDoubleFunction;

public class GaugeMetricsSample<T> extends MetricsSample{
    private T value;

    private ToDoubleFunction<T> apply;

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
