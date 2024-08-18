package www.raven.ospp;

import com.ctrip.framework.apollo.ConfigService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import www.raven.ospp.controller.TestController;

@SpringBootApplication
@Slf4j
public class TestApplication {

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(TestApplication.class, args);
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleAtFixedRate(() -> {
          TestController.test();
          ConfigService.getConfig("application");
          log.info(ConfigService.getConfigMonitor().getExporterData());
          log.info(ConfigService.getConfigMonitor().getExceptionMonitorApi().getApolloConfigExceptionList()
              .toString());
          log.info("{}", ConfigService.getConfigMonitor().getNamespaceMonitorApi()
              .getNamespaceMetrics().get("application").getUsageCount());
        }
        , 5, 5, java.util.concurrent.TimeUnit.SECONDS);
  }
}
