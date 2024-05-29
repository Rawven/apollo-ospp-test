package www.raven.ospp.metrics.collector.internal;

import java.util.List;

public interface MetricsCollectorManager {
    List<MetricsCollector> getCollectors();
}
