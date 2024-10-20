package www.raven.ospp.controller;

import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.monitor.api.ConfigMonitor;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@Slf4j
public class TestController {
  
    @Value("${secret:secret}")
    private String secret;
    
    @PostConstruct
    public void init() {
       log.info("TestController: {}", secret);  
    }

  @GetMapping("/metrics")
  public String metrics() {
    ConfigMonitor configMonitor = ConfigService.getConfigMonitor();
    return configMonitor.getExporterData();
  }
}
