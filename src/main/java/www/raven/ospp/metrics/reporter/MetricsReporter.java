package com.ctrip.framework.apollo.metrics.reporter;

import com.ctrip.framework.apollo.metrics.model.CounterMetricsSample;
import com.ctrip.framework.apollo.metrics.model.GaugeMetricsSample;

public interface MetricsReporter {

    void init();

    /**
     * 用于注册Counter类型的指标
     */
    void registerCounterSample(CounterMetricsSample sample);

    /**
     * 用于注册Gauge类型的指标
     */
    void registerGaugeSample(GaugeMetricsSample sample);

    /**
     * 收集的指标结果
     *
     * @return {@link String }
     */
    String response();
}
