import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     没有在run方法上加synchronized是：
 * @details         301
 * @details         505
 * @details         302
 * @details         303
 * @details         404
 * @details         505
 * @details     代表运行结果正确，执行顺序随机
 * @details     在run方法上加synchronized是：
 * @details         101
 * @details         202
 * @details         303
 * @details         404
 * @details         505
 * @details         505
 * @details     代表运行结果正确，依顺序顺序
 */
public class Code06_Atomic不保证方法的顺序 {
    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread[] ts = new Thread[100];
        for (int i = 0; i < 5; i ++) {
            ts[i] = new Thread(r);
        }
        for (int i = 0; i < 5; i ++) {
            ts[i].start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(r.count);
    }
    
    static class MyRunnable implements Runnable {
        private AtomicInteger count = new AtomicInteger(0);
        @Override
        public synchronized void run() {
//      public void run() {
            count.addAndGet(100);
            System.out.println(count.addAndGet(1));
        }
    }
}
