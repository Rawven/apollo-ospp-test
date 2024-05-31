package www.raven.ospp.metrics.model;

import java.util.Map;
import www.raven.ospp.metrics.util.MeterType;

public class MetricsSample {
    protected String name;
    protected MeterType type;
    protected Map<String, String> tags;

    public String getName() {
        return name;
    }

    public MeterType getType() {
        return type;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTag(Map<String, String> tags) {
        this.tags = tags;
    }
}
