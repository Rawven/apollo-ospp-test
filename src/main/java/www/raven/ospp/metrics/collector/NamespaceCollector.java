package com.ctrip.framework.apollo.metrics.collector;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.metrics.MBean.NamespaceMetricsMBean;
import com.ctrip.framework.apollo.metrics.MetricsEvent;
import com.ctrip.framework.apollo.metrics.model.CounterMetricsSample;
import com.ctrip.framework.apollo.metrics.model.MetricsSample;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@SuppressWarnings("unchecked")
public class NamespaceCollector implements MetricsCollector{

    private NamespaceMetricsMBean namespaceMetricsMBean;

    @Override
    public boolean isSupport(String tag) {
        // if(event instance of xxx)
        return false;
    }

    @Override
    public void collect(MetricsEvent event) {
           //logic to collect metrics
        if(event.getTag() == "namespace.configMap"){
            Map<String, Config> configMap = (Map<String, Config>) event.getObject();
            namespaceMetricsMBean.setNamespaceNum(configMap.size());
            //...logic
        }
    }

    @Override
    public boolean isSamplesUpdated() {
        return false;
    }

    @Override
    public List<MetricsSample> export() {
        List<MetricsSample> result = new ArrayList<>();
        collectNamespaceNum(result);
        //logic to export metrics
        return result;
    }


    private void collectNamespaceNum(List<MetricsSample> result) {
        long num = namespaceMetricsMBean.getNamespaceNum();
        CounterMetricsSample sample = new CounterMetricsSample(num);
        result.add(sample);

    }
}
