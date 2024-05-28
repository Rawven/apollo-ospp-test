package com.ctrip.framework.apollo.metrics.reporter.internal;

import com.ctrip.framework.apollo.core.utils.ClassLoaderUtil;
import com.ctrip.framework.apollo.metrics.reporter.MetricsReporter;
import com.ctrip.framework.apollo.metrics.reporter.MetricsReporterManager;
import com.ctrip.framework.apollo.spring.metrics.SpringMetricsReporter;

public class DefaultMetricsReporterManager implements MetricsReporterManager {
    private MetricsReporter reporter;

    public DefaultMetricsReporterManager() {
        if (ClassLoaderUtil.isClassPresent("org.springframework.boot.actuate")) {
            reporter =  new SpringMetricsReporter("no need");
        } else if(ClassLoaderUtil.isClassPresent("io.prometheus")) {
            reporter = new PrometheusMetricReporter("http://domain:9090");
        }
    }
    @Override
    public MetricsReporter getMetricsReporter() {
        return reporter;
    }
}
