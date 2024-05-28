package com.ctrip.framework.apollo.metrics.MBean;

import com.ctrip.framework.apollo.core.utils.ClassLoaderUtil;
import com.ctrip.framework.apollo.metrics.util.JMXUtil;
import com.ctrip.framework.apollo.util.ConfigUtil;

public class MetricsDataManager implements MetricsManagerMBean{
    private NamespaceMetrics namespaceMetrics = new NamespaceMetrics();
    private RepositoryChangeMetrics repositoryChangeMetrics = new RepositoryChangeMetrics();

    public MetricsDataManager() {
        if(!ClassLoaderUtil.isClassPresent("org.springframework.boot.actuate")) {
            JMXUtil.register("com.ctrip.framework.apollo:type=Metric", this);
        }
    }
    //method
}
