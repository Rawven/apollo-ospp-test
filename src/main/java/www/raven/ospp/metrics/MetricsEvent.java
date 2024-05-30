package www.raven.ospp.metrics;

public class MetricsEvent {
    private String tag;
    private Object object;


    private MetricsEvent(Builder builder) {
        this.tag = builder.tag;
        this.object = builder.object;
    }


    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String tag;
        private Object object;

        // 设置 tag 的方法
        public Builder withTag(String tag) {
            this.tag = tag;
            return this;
        }

        // 设置 object 的方法
        public Builder withObject(Object object) {
            this.object = object;
            return this;
        }

        // 构建 MetricsEvent 对象
        public MetricsEvent build() {
            return new MetricsEvent(this);
        }
    }

    public String getTag() {
        return tag;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "MetricsEvent{" +
            "tag='" + tag + '\'' +
            ", object=" + object +
            '}';
    }
}
