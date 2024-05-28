package com.ctrip.framework.apollo.metrics.collector;

import com.ctrip.framework.apollo.build.ApolloInjector;
import com.ctrip.framework.apollo.metrics.reporter.MetricsReporterManager;
import java.util.ArrayList;
import java.util.List;

public class DefaultMetricsCollectorManager implements MetricsCollectorManager {

    private List<MetricsCollector> collectors = new ArrayList<>();
    private DefaultMetricsCollectorManager() {
        collectors.add(new NamespaceCollector());
        collectors.add(new RepositoryChangeCollector());
    }
    @Override
    public List<MetricsCollector> getCollectors() {
        return collectors;
    }


}
