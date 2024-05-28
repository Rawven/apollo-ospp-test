package com.ctrip.framework.apollo.metrics.reporter.internal;

import com.ctrip.framework.apollo.core.utils.ApolloThreadFactory;
import com.ctrip.framework.apollo.metrics.model.CounterMetricsSample;
import com.ctrip.framework.apollo.metrics.model.GaugeMetricsSample;
import com.ctrip.framework.apollo.metrics.reporter.AbstractMetricsReporter;
import com.ctrip.framework.apollo.metrics.reporter.MetricsReporter;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrometheusMetricReporter extends AbstractMetricsReporter implements MetricsReporter {
    private static final Logger logger = LoggerFactory.getLogger(
        PrometheusMetricReporter.class);
    private final static ScheduledExecutorService m_executorService;
    private final CollectorRegistry registry;
    private final PushGateway pushGateway;

    static {
        m_executorService = Executors.newScheduledThreadPool(1,
            ApolloThreadFactory.create("RemoteConfigRepository", true));
    }

    public PrometheusMetricReporter(String url) {
        super(url);
        this.registry = new CollectorRegistry();
        this.pushGateway = new PushGateway(url);
    }

    @Override
    public void doInit() {
        openMetricsPort();
    }

    protected void initSchdulePushJob() {
        m_executorService.scheduleAtFixedRate(() -> {
            try {
                pushGateway.pushAdd(registry, "apollo");
            } catch (Throwable ex) {
                logger.error("Push metrics to Prometheus failed", ex);
            }
        }, getInitialDelay(), getPeriod(), TimeUnit.MILLISECONDS);
    }

    protected void openMetricsPort() {
        // open metrics port
    }

    @Override
    public void registerCounterSample(CounterMetricsSample sample) {
        Counter counter = Counter.build()
            .name(sample.getName())
            .help("Counter for " + sample.getName())
            .register(registry);
        counter.inc(sample.getValue());
    }

    @Override
    public void registerGaugeSample(GaugeMetricsSample sample) {
        Gauge gauge = Gauge.build()
            .name(sample.getName())
            .help("Gauge for " + sample.getName())
            .register(registry);
        gauge.set(sample.getApplyValue());
    }

    @Override
    public String response() {
        StringWriter writer = new StringWriter();
        try {
            TextFormat.write004(writer, registry.metricFamilySamples());
        } catch (IOException e) {
            logger.error("Write metrics to Prometheus format failed", e);
        }
        return writer.toString();
    }
}