/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     wait的运行输出：
 * @details         begin wait
 * @details         begin wait
 * @details         (程序没有退出，在等待)
 * @details     sleep的运行输出：
 * @details         begin wait
 * @details           end wait
 * @details         begin wait
 * @details           end wait
 * @details         (程序退出)
 */
public class Code09_wait释放锁_sleep不释放锁 {
    static class Service extends Thread {
        Object lock = null;
        public Service(Object lock) {
            this.lock = lock;
        }
        @Override
        public void run() {
            test(lock);
        }
        public void test(Object lock) {
            System.out.println("在Service类中");
        }
    }
    static class Service_wait extends Service {
        public Service_wait(Object lock) {
            super(lock);
        }
        public void test(Object lock) {
            try {
                synchronized (lock) {
                    System.out.println("begin wait");
                    lock.wait();
                    System.out.println("  end wait");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Service_sleep extends Service {
        public Service_sleep(Object lock) {
            super(lock);
        }
        public void test(Object lock) {
            try {
                synchronized (lock) {
                    System.out.println("begin wait");
                    Thread.sleep(1000);
                    System.out.println("  end wait");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Object lock = new Object();
//      Service s1 = new Service_wait(lock);
//      Service s2 = new Service_wait(lock);
        Service s1 = new Service_sleep(lock);
        Service s2 = new Service_sleep(lock);
        s1.start();
        s2.start();
    }
}
