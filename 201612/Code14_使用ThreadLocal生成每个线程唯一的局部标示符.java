import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     JDK6参考中的一份代码。
 * @details     生成对每一个线程唯一的标示符。
 * @details     第一次调用时候，分配。后续调用不会更改。
 */
public class Code14_使用ThreadLocal生成每个线程唯一的局部标示符 {
    static class UniqueThreadIdGenerator {
        private static final AtomicInteger uniqueId = new AtomicInteger(0);
        private static final ThreadLocal<Integer> uniqueNum = 
                new ThreadLocal<Integer>() {
                    @Override
                    protected Integer initialValue() {
                        return uniqueId.getAndIncrement();
                    };
                };
        public static int getCurrentThreadId() {
            return uniqueNum.get();
        }
    }
    public static void main(String[] args) {
        Thread[] ts = new Thread[20];
        for (int i = 0; i < ts.length; i ++) {
            ts[i] = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        int id = UniqueThreadIdGenerator.getCurrentThreadId();
                        System.out.println(Thread.currentThread().getName() + "..." + id);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                    }//while
                }//run
            };//Thread
        }//for
        for (Thread t : ts)
            t.start();
    }
}
