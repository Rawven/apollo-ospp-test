package www.raven.ospp;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.ctrip.framework.apollo.monitor.api.ApolloClientBootstrapArgsMonitorApi;
import com.ctrip.framework.apollo.monitor.api.ApolloClientExceptionMonitorApi;
import com.ctrip.framework.apollo.monitor.api.ApolloClientNamespaceMonitorApi;
import com.ctrip.framework.apollo.monitor.api.ApolloClientThreadPoolMonitorApi;
import com.ctrip.framework.apollo.monitor.api.ConfigMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
            Config appConfig = ConfigService.getAppConfig();
            log.info("AppConfig: {}", appConfig.getProperty("secret", "NoSecret"));
            ConfigMonitor configMonitor = ConfigService.getConfigMonitor();
            ApolloClientExceptionMonitorApi exceptionMonitorApi = configMonitor.getExceptionMonitorApi();
            List<Exception> apolloConfigExceptionList = exceptionMonitorApi.getApolloConfigExceptionList();
            log.info("ApolloConfigExceptionList: {}", apolloConfigExceptionList);
            Integer exceptionCountFromStartup = exceptionMonitorApi.getExceptionCountFromStartup();
            log.info("ExceptionCountFromStartup: {}", exceptionCountFromStartup);
            ApolloClientNamespaceMonitorApi namespaceMonitorApi = configMonitor.getNamespaceMonitorApi();
            Map<String, ApolloClientNamespaceMonitorApi.NamespaceMetrics> namespaceMetrics = namespaceMonitorApi.getNamespaceMetrics();
            log.info("NamespaceMetrics: {}", namespaceMetrics);
            ApolloClientBootstrapArgsMonitorApi runningParamsMonitorApi = configMonitor.getRunningParamsMonitorApi();
            Map<String, Object> bootstrapArgs = runningParamsMonitorApi.getBootstrapArgs();
            log.info("BootstrapArgs: {}", bootstrapArgs);
            String metaLatestFreshTime = runningParamsMonitorApi.getMetaLatestFreshTime();
            log.info("MetaLatestFreshTime: {}", metaLatestFreshTime);
            ApolloClientThreadPoolMonitorApi threadPoolMonitorApi = configMonitor.getThreadPoolMonitorApi();
            ApolloClientThreadPoolMonitorApi.ApolloThreadPoolInfo abstractConfigFileThreadPoolInfo = threadPoolMonitorApi.getAbstractConfigFileThreadPoolInfo();
            log.info("AbstractConfigFileThreadPoolInfo: {}", abstractConfigFileThreadPoolInfo);
            ApolloClientThreadPoolMonitorApi.ApolloThreadPoolInfo abstractConfigThreadPoolInfo =
                    threadPoolMonitorApi.getAbstractConfigThreadPoolInfo();
            log.info("ConfigServiceThreadPoolInfo: {}", abstractConfigThreadPoolInfo);
            ApolloClientThreadPoolMonitorApi.ApolloThreadPoolInfo metricsExporterThreadPoolInfo = threadPoolMonitorApi.getMetricsExporterThreadPoolInfo();
            log.info("MetricsExporterThreadPoolInfo: {}", metricsExporterThreadPoolInfo);
            ApolloClientThreadPoolMonitorApi.ApolloThreadPoolInfo remoteConfigRepositoryThreadPoolInfo = threadPoolMonitorApi.getRemoteConfigRepositoryThreadPoolInfo();
            log.info("RemoteConfigRepositoryThreadPoolInfo: {}", remoteConfigRepositoryThreadPoolInfo);
            String exporterData = ConfigService.getConfigMonitor().getExporterData();
            log.info(exporterData);
        } catch (Exception e) {
            log.error("Error fetching exporter data", e);
        }

    }, 1,3, java.util.concurrent.TimeUnit.SECONDS);
  }
}
