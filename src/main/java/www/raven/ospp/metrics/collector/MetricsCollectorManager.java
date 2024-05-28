package com.ctrip.framework.apollo.metrics.collector;

import java.util.List;

public interface MetricsCollectorManager {
    List<MetricsCollector> getCollectors();
}
