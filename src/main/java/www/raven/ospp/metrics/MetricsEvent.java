package com.ctrip.framework.apollo.metrics;

public class MetricsEvent<T> {
    private String tag;
    private T object;


    private MetricsEvent(Builder<T> builder) {
        this.tag = builder.tag;
        this.object = builder.object;
    }


    public static <T> Builder<T> builder() {
        return new Builder<>();
    }


    public static class Builder<T> {
        private String tag;
        private T object;

        // 设置 tag 的方法
        public Builder<T> withTag(String tag) {
            this.tag = tag;
            return this;
        }

        // 设置 object 的方法
        public Builder<T> withObject(T object) {
            this.object = object;
            return this;
        }

        // 构建 MetricsEvent 对象
        public MetricsEvent<T> build() {
            return new MetricsEvent<>(this);
        }
    }

    public String getTag() {
        return tag;
    }

    public T getObject() {
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
