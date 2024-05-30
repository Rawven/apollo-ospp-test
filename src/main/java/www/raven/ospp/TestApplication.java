package www.raven.ospp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import www.raven.ospp.metrics.MetricsCollectHelper;

@SpringBootApplication
public class TestApplication {

        public static void main(String[] args) {
            SpringApplication.run(TestApplication.class, args);
        }
}
