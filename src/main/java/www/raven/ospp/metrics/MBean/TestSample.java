package www.raven.ospp.metrics.MBean;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import www.raven.ospp.metrics.MBean.internal.AbstractMBean;

public class TestSample extends AbstractMBean implements TestSampleMBean {
    private AtomicInteger num = new AtomicInteger(0);
    private AtomicLong time = new AtomicLong(0);
    private String string1;

    @Override
    public int getNum() {
        return num.get();
    }

    @Override
    public void incNum() {
        num.incrementAndGet();
    }

    @Override
    public long getTime() {
        return time.get();
    }

    @Override
    public void setTime(long time) {
        this.time.set(time);
    }

    @Override
    public void test() {
        System.out.println(num);
    }

    @Override
    public String getString1() {
        return string1;
    }

    @Override
    public void setString1(String string1) {
        this.string1 = string1;
    }

    @Override
    public String toString() {
        return "TestSample{" +
            "num=" + num.get() +
            ", time=" + time.get() +
            '}';
    }
}
