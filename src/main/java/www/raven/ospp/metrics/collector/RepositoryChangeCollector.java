package com.ctrip.framework.apollo.metrics.collector;

import com.ctrip.framework.apollo.build.ApolloInjector;
import com.ctrip.framework.apollo.metrics.MBean.RepositoryChangeMetrics;
import com.ctrip.framework.apollo.metrics.MBean.RepositoryChangeMetricsMBean;
import com.ctrip.framework.apollo.metrics.MetricsEvent;
import com.ctrip.framework.apollo.metrics.model.MetricsSample;
import java.util.ArrayList;
import java.util.List;

public class RepositoryChangeCollector implements MetricsCollector {
    private RepositoryChangeMetricsMBean repositoryChangeMetrics = ApolloInjector.getInstance(RepositoryChangeMetrics.class);
    @Override
    public boolean isSupport(String tag) {
        return false;
    }

    @Override
    public void collect(MetricsEvent event) {

    }

    @Override
    public boolean isSamplesUpdated() {
        return false;
    }

    @Override
    public List<MetricsSample> export() {
        return new ArrayList<>();
    }
}
