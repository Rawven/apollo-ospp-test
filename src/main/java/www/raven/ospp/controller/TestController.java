package www.raven.ospp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import www.raven.ospp.metrics.MetricsCollectHelper;
import www.raven.ospp.metrics.MetricsEvent;
import www.raven.ospp.metrics.collector.internal.TestCollector;

@RestController
@ResponseBody
public class TestController {

    @PostMapping("/test")
    public String test() {
        MetricsCollectHelper.pushMetricsEvent(MetricsEvent.builder().withName(TestCollector.NUM_NAME).build());
        return "test";
    }

    @PostMapping("/test1")
    public String test1() {
        MetricsCollectHelper.pushMetricsEvent(MetricsEvent.builder().withName(TestCollector.STRING_NAME)
            .withData("apollo").build());
        return "test1";
    }

    @PostMapping("/test2")
    public String test2() {
        MetricsCollectHelper.pushMetricsEvent(MetricsEvent.builder().withName(TestCollector.TIME_NAME)
            .withData(System.currentTimeMillis()).build());
        return "test2";
    }



    @GetMapping("/prometheus")
    public String response() {
        return MetricsCollectHelper.getMetricsExportData();
    }

}
