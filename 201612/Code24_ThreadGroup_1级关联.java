/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     执行结果：
 * @details         活动线程数：0
 * @details         活动线程数：2
 * @details         Thread-Name is Thread-3
 * @details         Thread-Name is Thread-2
 * @details         Thread-Name is Thread-3
 * @details         Thread-Name is Thread-2
 */
public class Code24_ThreadGroup_1级关联 {
    static class TA extends Thread {
        @Override
        public void run() {
            try {
                while (! Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread-Name is " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class TB extends Thread {
        @Override
        public void run() {
            try {
                while (! Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread-Name is " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        TA ra = new TA();
        TB rb = new TB();
        ThreadGroup tgs = new ThreadGroup("线程组的名称");
        Thread ta = new Thread(tgs, ra);
        Thread tb = new Thread(tgs, rb);
        System.out.println("活动线程数："+tgs.activeCount());
        ta.start();
        tb.start();
        System.out.println("活动线程数："+tgs.activeCount());
    }
}
