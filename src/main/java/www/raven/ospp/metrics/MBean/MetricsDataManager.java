package www.raven.ospp.metrics.MBean;

import www.raven.ospp.metrics.util.ClassLoaderUtil;
import www.raven.ospp.metrics.util.JMXUtil;
import www.raven.ospp.metrics.util.UtilInjector;

public class MetricsDataManager implements MetricsDataManagerMBean{
   private TestSampleMBean testSample = UtilInjector.getInstance(TestSampleMBean.class);;
    public MetricsDataManager() {
        if(!ClassLoaderUtil.isClassPresent("org.springframework.boot.actuate")) {
            JMXUtil.register("com.ctrip.framework.apollo:type=Metric", this);
        }
    }

    @Override
    public String getTestSampleMBean() {
        return testSample.toString();
    }
}
