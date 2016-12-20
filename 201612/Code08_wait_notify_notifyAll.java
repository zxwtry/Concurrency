/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     runTB --> 唤醒一个等待的进程，其余一直等待。
 * @details     runTC --> 唤醒全部等待的进程，会执行完。
 */
public class Code08_wait_notify_notifyAll {
    static class TA extends Thread {
        private Object o = null; 
        int i = 0;
        public TA(Object o, int i) {
            this.o = o;
            this.i = i;
        }
        @Override
        public void run() {
            synchronized (o) {
                System.out.println(i+": run");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i+": end");
            }
        }
    }
    static class TB extends Thread {
        private Object o = null;
        public TB(Object o) {
            this.o = o;
        }
        @Override
        public void run() {
            synchronized (o) {
                o.notify();
            }
        }
    }
    static class TC extends Thread {
        private Object o = null;
        public TC(Object o) {
            this.o = o;
        }
        @Override
        public void run() {
            synchronized (o) {
                o.notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        runTB();
//      runTC();
    }
    static void runTC() {
        Object o = new Object();
        TA[] ts = new TA[10];
        for (int i = 0; i < ts.length; i ++)
            ts[i] = new TA(o, i);
        for (int i = 0; i < ts.length; i ++)
            ts[i].start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TC tc =  new TC(o);
        tc.start();
    }
    static void runTB() {
        Object o = new Object();
        TA[] ts = new TA[10];
        for (int i = 0; i < ts.length; i ++)
            ts[i] = new TA(o, i);
        for (int i = 0; i < ts.length; i ++)
            ts[i].start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TB tb =  new TB(o);
        tb.start();
    }
}

