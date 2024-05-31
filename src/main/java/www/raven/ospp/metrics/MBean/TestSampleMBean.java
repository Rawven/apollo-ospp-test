package www.raven.ospp.metrics.MBean;

public interface TestSampleMBean {
    int getNum();

    void incNum();

    long getTime();

    void setTime(long time);

    void test();

    String getString1();

    void setString1(String string1);
}
