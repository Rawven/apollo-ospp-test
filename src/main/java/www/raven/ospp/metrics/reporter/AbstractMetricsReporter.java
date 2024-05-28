package com.ctrip.framework.apollo.metrics.reporter;

import com.ctrip.framework.apollo.build.ApolloInjector;
import com.ctrip.framework.apollo.core.utils.ApolloThreadFactory;
import com.ctrip.framework.apollo.metrics.collector.MetricsCollector;
import com.ctrip.framework.apollo.metrics.collector.MetricsCollectorManager;
import com.ctrip.framework.apollo.metrics.model.CounterMetricsSample;
import com.ctrip.framework.apollo.metrics.model.GaugeMetricsSample;
import com.ctrip.framework.apollo.metrics.model.MetricsSample;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractMetricsReporter implements MetricsReporter {

    protected String url;
    private final static ScheduledExecutorService m_executorService;
    private final MetricsCollectorManager metricsCollectorManager = ApolloInjector.getInstance(MetricsCollectorManager.class);

    public AbstractMetricsReporter(String url) {
        //....
        this.url = url;
    }
    @Override
    public void init() {
        //...
        doInit();
        initScheduleMetricsCollectSync();
    }

    protected abstract void doInit();

    static {
        m_executorService = Executors.newScheduledThreadPool(1,
            ApolloThreadFactory.create("RemoteConfigRepository", true));
    }

    private void initScheduleMetricsCollectSync() {
        m_executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    updateMetricsData();
                } catch (Throwable ex) {
                    //ignore
                }
            }
        }, getInitialDelay(), getPeriod(), TimeUnit.MILLISECONDS);
    }

    private void updateMetricsData() {
        for (MetricsCollector collector : metricsCollectorManager.getCollectors()) {
            if(!collector.isSamplesUpdated()){
                continue;
            }
            List<MetricsSample> export = collector.export();
            for (MetricsSample metricsSample : export) {
                registerSample(metricsSample);
            }
        }

    }

    private void registerSample(MetricsSample sample) {
        switch (sample.getType()) {
            case GAUGE:
                registerGaugeSample((GaugeMetricsSample) sample);
                break;
            case COUNTER:
                registerCounterSample((CounterMetricsSample) sample);
                break;
            default:
        }
    }


    protected long getPeriod() {
        return 0;
    }

    protected long getInitialDelay() {
        return 0;
    }

}
