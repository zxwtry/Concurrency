import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     公平锁和非公平锁
 * @details     公平锁运行结果：
 * @details         ###Thread-0运行了
 * @details         ###Thread-3运行了
 * @details         ###Thread-1运行了
 * @details         ###Thread-2运行了
 * @details         ###Thread-5运行了
 * @details         ###Thread-4运行了
 * @details         ThreadName=Thread-0获得锁
 * @details         ###Thread-6运行了
 * @details         ###Thread-7运行了
 * @details         ###Thread-8运行了
 * @details         ###Thread-9运行了
 * @details         ThreadName=Thread-3获得锁
 * @details         ThreadName=Thread-1获得锁
 * @details         ThreadName=Thread-2获得锁
 * @details         ThreadName=Thread-5获得锁
 * @details         ThreadName=Thread-4获得锁
 * @details         ThreadName=Thread-6获得锁
 * @details         ThreadName=Thread-7获得锁
 * @details         ThreadName=Thread-8获得锁
 * @details         ThreadName=Thread-9获得锁
 * @details     非公平锁运行结果：
 * @details         ###Thread-1运行了
 * @details         ###Thread-5运行了
 * @details         ThreadName=Thread-1获得锁
 * @details         ###Thread-0运行了
 * @details         ThreadName=Thread-0获得锁
 * @details         ThreadName=Thread-5获得锁
 * @details         ###Thread-4运行了
 * @details         ###Thread-3运行了
 * @details         ###Thread-8运行了
 * @details         ThreadName=Thread-4获得锁
 * @details         ###Thread-9运行了
 * @details         ThreadName=Thread-9获得锁
 * @details         ThreadName=Thread-3获得锁
 * @details         ###Thread-2运行了
 * @details         ThreadName=Thread-2获得锁
 * @details         ThreadName=Thread-8获得锁
 * @details         ###Thread-6运行了
 * @details         ThreadName=Thread-6获得锁
 * @details         ###Thread-7运行了
 * @details         ThreadName=Thread-7获得锁
 */
public class Code16_公平锁和非公平锁 {
    static class Service {
        private Lock l;
        public Service(boolean isFair) {
            super();
            l = new ReentrantLock(isFair);
        }
        public void serviceMethod() {
            try {
                l.lock();
                System.out.println("ThreadName="+Thread.currentThread().getName()+"获得锁");
            } finally {
                l.unlock();
            }
        }
    }
    public static void main(String[] args) {
        final Service service = new Service(false);
//      final Service service = new Service(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("###"+Thread.currentThread().getName()+"运行了");
                service.serviceMethod();
            }
        };
        Thread[] ts = new Thread[10];
        for (int i = 0; i < ts.length; i ++)
            ts[i] = new Thread(runnable);
        for (Thread t : ts)
            t.start();
    }
}
