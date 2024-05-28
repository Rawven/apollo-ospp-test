package com.ctrip.framework.apollo.metrics;

import com.ctrip.framework.apollo.build.ApolloInjector;
import com.ctrip.framework.apollo.metrics.collector.MetricsCollector;
import com.ctrip.framework.apollo.metrics.collector.DefaultMetricsCollectorManager;
import com.ctrip.framework.apollo.metrics.collector.MetricsCollectorManager;
import com.ctrip.framework.apollo.metrics.reporter.MetricsReporterManager;
import com.ctrip.framework.apollo.util.ConfigUtil;

public abstract class MetricsCollectHelper {

    private static MetricsCollectorManager collectorManager;
    private static MetricsReporterManager reporterManager;

    static {
        collectorManager = ApolloInjector.getInstance(MetricsCollectorManager.class);
        reporterManager = ApolloInjector.getInstance(MetricsReporterManager.class);
    }
    public static <T>void pushMetricsObject(T object, String key) {
        for (MetricsCollector collector : collectorManager.getCollectors()) {
            if (collector.isSupport(key)) {
                collector.collect(MetricsEvent.builder().withObject(object).withTag(key).build());
                return;
            }
        }
    }

    public static String getMetricsExportData() {
        return reporterManager.getMetricsReporter().response();
    }


}
