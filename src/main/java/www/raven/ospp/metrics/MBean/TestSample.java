package www.raven.ospp.metrics.MBean;

public class TestSample implements TestSampleMBean{
    private int num;
    @Override
    public int getNum() {
        return num;
    }

    @Override
    public void setNum(int test) {
        this.num = test;
    }

    @Override
    public void test() {
        System.out.println(num);
    }

    @Override
    public String toString() {
        return "TestSample{" +
                "num=" + num +
                '}';
    }
}
