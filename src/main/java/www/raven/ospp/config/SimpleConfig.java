package www.raven.ospp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class SimpleConfig {
    @Value("${apollo.metrics.protocol.type}")
    private String metricsProtocolType;
    @Value("${apollo.metrics.protocol.url}")
    private String metricsProtocolUrl;
    @Value("${apollo.metrics.enabled}")
    private boolean metricsEnabled;
}
