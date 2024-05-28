package www.raven.ospp.metrics.MBean;

public class TestSample extends AbstractMBean implements TestSampleMBean{
    private int num;
    private long time;
    @Override
    public int getNum() {
        return num;
    }

    @Override
    public void setNum(int test) {
        this.num = test;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public void test() {
        System.out.println(num);
    }

    @Override
    public String toString() {
        return "TestSample{" +
                "num=" + num +
                ", time=" + time +
                '}';
    }
}
