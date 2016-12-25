import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     读写锁：ReentrantReadWriteLock
 * @details     读读共享，读写互斥，写读互斥，写写互斥
 */
public class Code18_读写锁ReentrantReadWriteLock {
    static class Service {
        private ReentrantReadWriteLock locks = new ReentrantReadWriteLock(true);
        public void read() {
            try {
                try {
                    locks.readLock().lock();
                    System.out.println("获取读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                    Thread.sleep(1000);
                } finally {
                    locks.readLock().unlock();
                    System.out.println("释放读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void write() {
            try {
                try {
                    locks.writeLock().lock();
                    System.out.println("获取写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                    Thread.sleep(1000);
                } finally {
                    locks.writeLock().unlock();
                    System.out.println("释放写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        final Service service = new Service();
        for (int i = 0; i < 10; i ++) {
            final int is = i;
            new Thread() {
                @Override
                public void run() {
                    if (is % 2 == 0)
                        service.read();
                    else
                        service.write();
                };
            }.start();
        }
    }
}
