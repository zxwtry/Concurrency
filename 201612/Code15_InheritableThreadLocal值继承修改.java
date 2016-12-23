import java.util.Date;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     使用InheritableThreadLocal类需要注意的一点是：
 * @details     如果子线程在取得值的同时，主线程将InheritableThreadLocal
 * @details     中的值进行更改，那么子线程取到的值还是旧值。
 * @details     运行结果：
 * @details     在Main线程中取值=1482502081654
 * @details     在Main线程中取值=1482502081654
 * @details     在Main线程中取值=1482502081654
 * @details     在Main线程中取值=1482502081654
 * @details     在Main线程中取值=1482502081654
 * @details     在TA线程中取值=1482502081654在子线程加的~~~
 * @details     在TA线程中取值=1482502081654在子线程加的~~~
 * @details     在TA线程中取值=1482502081654在子线程加的~~~
 * @details     在TA线程中取值=1482502081654在子线程加的~~~
 * @details     在TA线程中取值=1482502081654在子线程加的~~~
 */
public class Code15_InheritableThreadLocal值继承修改 {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 5; i ++) {
                System.out.println("在Main线程中取值=" + Tools.mitl.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            TA a = new TA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class MyITL extends InheritableThreadLocal<String> {
        @Override
        protected String initialValue() {
            return new Date().getTime()+"";
        }
        @Override
        protected String childValue(String parentValue) {
            return parentValue + "在子线程加的~~~";
        }
    }
    static class Tools {
        public static MyITL mitl = new MyITL();
    }
    static class TA extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i ++) {
                    System.out.println("在TA线程中取值=" + Tools.mitl.get());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
