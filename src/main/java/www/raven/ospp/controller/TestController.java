package www.raven.ospp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import www.raven.ospp.metrics.MetricsCollectHelper;

@RestController
@ResponseBody
public class TestController {

    @PostMapping("/test")
    public String test() {
        MetricsCollectHelper.pushMetricsObject("test", "test");
        return "test";
    }

    @PostMapping("/test2")
    public String test2() {
        MetricsCollectHelper.pushMetricsObject(System.currentTimeMillis(), "test2");
        return "test2";
    }

    @PostMapping("/response")
    public String response() {
        return MetricsCollectHelper.getMetricsExportData();
    }

}
