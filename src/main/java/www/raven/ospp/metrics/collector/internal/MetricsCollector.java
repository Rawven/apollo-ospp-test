package www.raven.ospp.metrics.collector.internal;

import java.util.List;
import www.raven.ospp.metrics.MetricsEvent;
import www.raven.ospp.metrics.model.MetricsSample;

public interface MetricsCollector {

    /**
     * 是否支持该指标
     */
    boolean isSupport(String tag);

    /**
     * 收集指标
     */
    void collect(MetricsEvent event);

    /**
     * 是否更新了指标样本
     *
     */
    boolean isSamplesUpdated();

    /**
     * 导出指标
     *
     */
    List<MetricsSample> export();



}
