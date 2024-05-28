package com.ctrip.framework.apollo.metrics.MBean;

import java.util.concurrent.atomic.AtomicLong;

public class NamespaceMetrics implements NamespaceMetricsMBean {
    private AtomicLong namespaceNum = new AtomicLong(0);
    @Override
    public void setNamespaceNum(long namespaceNum) {
        this.namespaceNum.set(namespaceNum);
    }

    @Override
    public long getNamespaceNum() {
        return namespaceNum.get();
    }
}
