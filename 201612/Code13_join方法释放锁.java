/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     执行就是：tb.join();和Thread.sleep(5000);二选一
 * @details     选tb.join();时，运行过程：
 * @details         in TA running!(0.5s)
 * @details         in TB running!(0.5s)
 * @details         in TB running!(0.5s)
 * @details     选Thread.sleep(5000);时，运行过程：
 * @details         in TA running!(5.5s)
 * @details         in TB running!(0.5s)
 * @details         in TB running!(0.5s)
 * @details     结果分析：
 * @details         1,  注意在TA中synchronized锁的是tb对象
 * @details         2,  tb.join();会释放tb的对象锁
 * @details         3,  Thread.sleep(5000);不会释放tb的对象锁(有点尴尬)
 * @details         3,  注意注意，重点是在tb.join();会释放tb的对象锁。
 */
public class Code13_join方法释放锁 {
    static class TB extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (this) {
                        Thread.sleep(500);
                        System.out.println("in TB running!");
                    }
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    static class TA extends Thread {
        TB tb = null;
        public TA(TB tb) {
            this.tb = tb;
        }
        @Override
        public void run() {
            try {
                synchronized (tb) {
                    System.out.println("in TA running!");
                    tb.start();
//                  tb.join();
                    Thread.sleep(5000);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        TB tb = new TB();
        TA ta = new TA(tb);
        ta.start();
    }
}
