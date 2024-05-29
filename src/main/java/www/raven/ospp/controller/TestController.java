package www.raven.ospp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import www.raven.ospp.metrics.MetricsCollectHelper;
import www.raven.ospp.metrics.collector.TestCollector;

@RestController
@ResponseBody
public class TestController {

    @PostMapping("/test")
    public String test() {
        //触发Counter类型指标
        MetricsCollectHelper.pushMetricsObject("num add", TestCollector.NUM_NAME);
        return "test";
    }

    @PostMapping("/test2")
    public String test2() {
        //触发Gauge类型指标
        MetricsCollectHelper.pushMetricsObject(System.currentTimeMillis(), TestCollector.TIME_NAME);
        return "test2";
    }

    @GetMapping("/prometheus")
    public String response() {
        return MetricsCollectHelper.getMetricsExportData();
    }

}
