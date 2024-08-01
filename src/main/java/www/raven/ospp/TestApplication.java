package www.raven.ospp;

import com.ctrip.framework.apollo.ConfigService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> {
          ConfigService.getConfig("application");
            log.info(ConfigService.getConfigMonitor().getDataWithCurrentMonitoringSystemFormat());
            log.info(ConfigService.getConfigMonitor().getExceptionMonitorApi().getExceptionDetails().toString());
            log.info("{}", ConfigService.getConfigMonitor().getNamespaceMonitorApi()
                  .getNamespaceUsageCount("application"));
        }
            , 5, 5, java.util.concurrent.TimeUnit.SECONDS);
    }
}
