package www.raven.ospp.metrics.MBean;

import org.springframework.jmx.support.JmxUtils;
import www.raven.ospp.metrics.util.JMXUtil;

public abstract class AbstractMBean {
    private String name;
    public AbstractMBean(){
        JMXUtil.register("www.raven.ospp:type="+this.getClass().getSimpleName(), this);
    }
}
