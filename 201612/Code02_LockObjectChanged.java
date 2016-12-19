/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        Main.java
 * @type        Main
 * @date        2016年12月19日 上午11:09:12
 * @details     锁对象的改变
 * @details     注释Thread.sleep(100);执行结果：
 * @details         A start 1482117753829
 * @details         A end 1482117755830
 * @details         B start 1482117755830
 * @details         B end 1482117757830
 * @details     不注释Thread.sleep(100);执行结果：
 * @details         A start 1482117822185
 * @details         B start 1482117822285
 * @details         A end 1482117824185
 * @details         B end 1482117824285
 * @details     注意这种程序的运行结果本身经常不唯一。
 * @details     这只是简化加demo。不要当真。
 */
public class Code02_LockObjectChanged {
    static class MyService {
        private String lock = "12";
        public void testMethod() {
            try {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " start " + System.currentTimeMillis());
                    lock = "34";
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadA extends Thread {
        MyService ms;
        public ThreadA(MyService ms) {
            this.ms = ms;
        }
        @Override
        public void run() {
            ms.testMethod();
        }
    }
    static class ThreadB extends Thread {
        MyService ms;
        public ThreadB(MyService ms) {
            this.ms = ms;
        }
        @Override
        public void run() {
            ms.testMethod();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyService ms = new MyService();
        ThreadA ta = new ThreadA(ms);
        ThreadA tb = new ThreadA(ms);
        ta.setName("A");
        tb.setName("B");
        ta.start();
        Thread.sleep(100);
        tb.start();
    }
}
