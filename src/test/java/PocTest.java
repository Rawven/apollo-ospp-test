import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {PocTest.class})
@Slf4j
public class PocTest {

  @JunitPerfConfig(duration = 1000,threads = 5)
  public void test() {
    Config config = ConfigService.getConfig("1");
    log.info(config.getPropertyNames().toString());
    List<String> exceptionNum = ConfigService.getConfigMonitor().getExceptionMonitorApi()
        .getExceptionDetails();
    log.info("{}", exceptionNum.toString());
  }
}
