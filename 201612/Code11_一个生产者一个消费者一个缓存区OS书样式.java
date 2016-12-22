/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     生产者和消费者模式
 * @details     一个生产者+一个消费者+一个缓存区
 * @details     执行过程：
 * @details         1生产一个产品
 * @details         2送到缓存区
 * @details         1生产一个产品
 * @details         3从缓存区拿一个产品
 * @details         4消费一个产品
 * @details         2送到缓存区
 * @details         1生产一个产品
 * @details         3从缓存区拿一个产品
 * @details         4消费一个产品
 * @details         2送到缓存区
 * @details         1生产一个产品
 * @details         3从缓存区拿一个产品
 * @details         4消费一个产品
 * @details         2送到缓存区
 * @details         1生产一个产品
 * @details         3从缓存区拿一个产品
 * @details         4消费一个产品
 */
public class Code11_一个生产者一个消费者一个缓存区OS书样式 {
    static class Lock {
        public int getCount() {
            return count;
        }
        public void increase() {
            synchronized (this) {
                this.count ++;
                this.notify();
            }
        }
        public void decrease() {
            try {
                synchronized (this) {
                    if (this.count == 0) this.wait();
                    this.count --;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        private int count = 0;
        public Lock(int count) {
            this.count = count;
        }
    }
    static Lock empty = new Lock(1);
    static Lock full = new Lock(0);
    static Lock mutex = new Lock(1);
    static class P extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("1生产一个产品");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                empty.decrease();
                mutex.decrease();
                System.out.println("2送到缓存区");
                mutex.increase();
                full.increase();
            }
        }
    }
    static class C extends Thread {
        @Override
        public void run() {
            while (true) {
                full.decrease();
                mutex.decrease();
                System.out.println("3从缓存区拿一个产品");
                mutex.increase();
                empty.increase();
                System.out.println("4消费一个产品");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        P p = new P();
        C c = new C();
        p.start();
        c.start();
    }
}
