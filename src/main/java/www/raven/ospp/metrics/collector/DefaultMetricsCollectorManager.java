package www.raven.ospp.metrics.collector;

import java.util.ArrayList;
import java.util.List;

public class DefaultMetricsCollectorManager implements MetricsCollectorManager {

    private List<MetricsCollector> collectors = new ArrayList<>();
    public DefaultMetricsCollectorManager() {
        collectors.add(new TestCollector());
    }
    @Override
    public List<MetricsCollector> getCollectors() {
        return collectors;
    }


}
