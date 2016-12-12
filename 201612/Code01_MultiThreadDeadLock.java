/**
 *  检查死锁的方法：
 *  1,  jps查看当前Java进程的PID
 *  2,  找到对应的PID
 *  3,  执行jstack -l pid(数值)
 *  4,  会显示非常多的信息，死锁相关的如下：
 *  Found one Java-level deadlock:
 *  =============================
 *  "Thread-2":
 *    waiting to lock monitor 0x00007f9e840062c8 (object 0x00000000f098f1e0, a java.lang.Object),
 *    which is held by "Thread-1"
 *  "Thread-1":
 *    waiting to lock monitor 0x00007f9e84004ed8 (object 0x00000000f098f1f0, a java.lang.Object),
 *    which is held by "Thread-2"
 *  
 *  Java stack information for the threads listed above:
 *  ===================================================
 *  "Thread-2":
 *      at Code01_MultiThreadDeadLock$T.run(Code01_MultiThreadDeadLock.java:68)
 *      - waiting to lock <0x00000000f098f1e0> (a java.lang.Object)
 *      - locked <0x00000000f098f1f0> (a java.lang.Object)
 *      at java.lang.Thread.run(Thread.java:745)
 *  "Thread-1":
 *      at Code01_MultiThreadDeadLock$T.run(Code01_MultiThreadDeadLock.java:60)
 *      - waiting to lock <0x00000000f098f1f0> (a java.lang.Object)
 *      - locked <0x00000000f098f1e0> (a java.lang.Object)
 *      at java.lang.Thread.run(Thread.java:745)
 *  
 *  Found 1 deadlock.
 */

public class Code01_MultiThreadDeadLock {
    public static void main(String[] args) throws Exception {
        T t = new T();
        t.setTag("a");
        Thread t1 = new Thread(t);
        t1.start();
        Thread.sleep(100);
        t.setTag("b");
        Thread t2 = new Thread(t);
        t2.start();
    }
    
    static class T extends Thread {
        private String tag = "";
        private Object lock1 = new Object();
        private Object lock2 = new Object();
        public void setTag(String tag) {
            this.tag = tag;
        }
        @Override
        public void run() {
            try {
                if (tag.equals("a")) {
                    synchronized (lock1) {
                        Thread.sleep(3000);
                        synchronized (lock2) {
                            System.out.println("lock1 --> lock2");
                        }
                    }
                }//tag--a
                if (tag.equals("b")) {
                    synchronized (lock2) {
                        Thread.sleep(3000);
                        synchronized (lock1) {
                            System.out.println("lock2 --> lock1");
                        }
                    }
                }//tag---b
            } catch (InterruptedException e) {
                e.printStackTrace();
            }//try-catch
        }//run
    }//T
    
}
