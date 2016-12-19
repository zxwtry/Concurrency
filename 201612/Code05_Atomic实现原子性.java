import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     Atomic实现原子性
 * @details     所有线程执行完之后，输出都是10000
 */
public class Code05_Atomic实现原子性 {
    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread[] ts = new Thread[100];
        for (int i = 0; i < 100; i ++) {
            ts[i] = new Thread(r);
        }
        for (int i = 0; i < 100; i ++) {
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
        public void run() {
            for (int i = 0; i < 100; i ++)
                count.incrementAndGet();
        }
    }
}
