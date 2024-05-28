package com.ctrip.framework.apollo.metrics.model;

public class CounterMetricsSample extends MetricsSample{
    private Double value;

    public CounterMetricsSample(long num) {
        this.value = (double) num;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
