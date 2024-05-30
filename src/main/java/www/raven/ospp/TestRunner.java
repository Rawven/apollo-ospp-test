package www.raven.ospp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import www.raven.ospp.config.SimpleConfig;
import www.raven.ospp.metrics.MetricsCollectHelper;

/**
 * test runner
 *
 * @author 刘家辉
 * @date 2024/05/30
 */
@Component
public class TestRunner implements CommandLineRunner {
    @Autowired
    private SimpleConfig config;
    @Override
    public void run(String... args) {
        MetricsCollectHelper.simpleInit(config.getMetricsProtocolUrl(),config.getMetricsProtocolType());
    }
}
