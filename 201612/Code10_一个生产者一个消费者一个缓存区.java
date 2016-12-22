/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     生产者和消费者模式
 * @details     一个生产者+一个消费者+一个缓存区
 */
public class Code10_一个生产者一个消费者一个缓存区 {
    static class VO {
        public static String v = "";
    }
    static class P {
        private String lock;
        public P(String lock) {this.lock = lock;}
        public void setV() {
            try {
                synchronized (lock) {
                    if (! VO.v.equals("")) lock.wait();
                    String v = System.currentTimeMillis() + "_" + System.nanoTime();
                    System.out.println("set的值是：" + v);
                    VO.v = v;
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class C {
        private String lock;
        public C(String lock) {this.lock = lock;}
        public void getV() {
            try {
                synchronized (lock) {
                    if (VO.v.equals("")) lock.wait();
                    System.out.println("get的值是："+VO.v);
                    VO.v = "";
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class TP extends Thread {
        private P p;
        public TP(P p) {this.p=p;};
        @Override
        public void run() {
            while (true) {
                p.setV();
            }
        }
    }
    static class TC extends Thread {
        private C c;
        public TC(C c) {this.c=c;};
        @Override
        public void run() {
            while (true) {
                c.getV();
            }
        }
    }
    public static void main(String[] args) {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        TP tp = new TP(p);
        TC tc = new TC(c);
        tp.start();
        tc.start();
    }
}
