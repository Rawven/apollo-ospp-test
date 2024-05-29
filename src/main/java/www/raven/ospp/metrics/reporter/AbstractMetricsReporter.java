package www.raven.ospp.metrics.reporter;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import www.raven.ospp.metrics.collector.MetricsCollector;
import www.raven.ospp.metrics.collector.MetricsCollectorManager;
import www.raven.ospp.metrics.model.CounterMetricsSample;
import www.raven.ospp.metrics.model.GaugeMetricsSample;
import www.raven.ospp.metrics.model.MetricsSample;
import www.raven.ospp.metrics.util.SimpleInjector;

import static www.raven.ospp.metrics.util.MeterType.COUNTER;
import static www.raven.ospp.metrics.util.MeterType.GAUGE;

@SuppressWarnings("all")
public abstract class AbstractMetricsReporter implements MetricsReporter {

    private static final Logger log = LoggerFactory.getLogger(AbstractMetricsReporter.class);
    protected String url;
    private final static ScheduledExecutorService m_executorService;
    private final MetricsCollectorManager metricsCollectorManager = SimpleInjector.getInstance(MetricsCollectorManager.class);

    public AbstractMetricsReporter(String url) {
        //....
        this.url = url;
        init();
    }

    @Override
    public void init() {
        //...
        doInit();
        initScheduleMetricsCollectSync();
    }

    protected abstract void doInit();

    static {
        m_executorService = Executors.newScheduledThreadPool(1);
    }

    private void initScheduleMetricsCollectSync() {
        log.info("Start to schedule metrics collect sync job");
        m_executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("Start to update metrics data job");
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
        if (sample.getType() == GAUGE) {
            registerGaugeSample((GaugeMetricsSample) sample);
        } else if (sample.getType() == COUNTER) {
            registerCounterSample((CounterMetricsSample) sample);
        }
    }


    protected long getPeriod() {
        return 5000;
    }

    protected long getInitialDelay() {
        return 5000;
    }

}
