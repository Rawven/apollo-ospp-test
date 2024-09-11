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
              try {
            String exporterData = ConfigService.getConfigMonitor().getExporterData();
            log.info(exporterData);
        } catch (Exception e) {
            log.error("Error fetching exporter data", e);
        }

    }, 1,3, java.util.concurrent.TimeUnit.SECONDS);
  }
}
