package www.raven.ospp.controller;

import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.monitor.api.ConfigMonitor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class TestController {

    @PostMapping("/test")
    public String test() {
       return "test";
    }

    @PostMapping("/test1")
    public String test1() {return "test1";
    }

    @PostMapping("/test2")
    public String test2() {
      return "test2";
    }



    @GetMapping("/metrics")
    public String response() {
        ConfigMonitor configMonitor = ConfigService.getConfigMonitor();
        return configMonitor.getDataWithCurrentMonitoringSystemFormat();
  }
}
