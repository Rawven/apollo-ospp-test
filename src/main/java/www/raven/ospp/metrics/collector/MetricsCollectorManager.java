package www.raven.ospp.metrics.collector;

import java.util.List;

public interface MetricsCollectorManager {
    List<MetricsCollector> getCollectors();
}
