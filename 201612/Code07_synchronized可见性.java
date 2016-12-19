/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     Service的rm方法
 * @details         Object o = new Object();
 * @details         while (ir) {
 * @details             synchronized (o) {
 * @details             }
 * @details         }
 * @details         会停 下来
 * @details         while (ir) {
 * @details             synchronized (new Object()) {
 * @details             }
 * @details         }
 * @details         不会停下来
 * @details         try {
 * @details             Thread.sleep(1);
 * @details         } catch (InterruptedException e) {
 * @details             e.printStackTrace();
 * @details         }
 * @details         在JDK7中，其实上面也会停下来。
 * @details         书上总是说-server会死循环。没实现出来。
 */
public class Code07_synchronized可见性 {
    static class Service {
        private boolean ir = true;      //isRun
        public void rm() {              //run method
            Object o = new Object();
            while (ir) {
                synchronized (o) {
                }
            }
            System.out.println("rm停下来了");
        }
        public void sm() {              //stop method
            ir = false;
        }
    }
    static class TA extends Thread {
        private Service s;
        public TA(Service s) {
            this.s = s;
        }
        @Override
        public void run() {
            s.rm();
        }
    }
    static class TB extends Thread {
        private Service s;
        public TB(Service s) {
            this.s = s;
        }
        @Override
        public void run() {
            s.sm();
        }
    }
    public static void main(String[] args) throws Exception {
        Service s = new Service();
        TA ta = new TA(s);
        TB tb = new TB(s);
        ta.start();
        Thread.sleep(1000);
        tb.start();
        System.out.println("已经发起停止命令");
    }
}
