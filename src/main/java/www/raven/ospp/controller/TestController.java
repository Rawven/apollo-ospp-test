package www.raven.ospp.controller;

import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.monitor.api.ConfigMonitor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class TestController {

  @GetMapping("/metrics")
  public String metrics() {
    ConfigMonitor configMonitor = ConfigService.getConfigMonitor();
    return configMonitor.getDataWithCurrentMonitoringSystemFormat();
  }
  
}
