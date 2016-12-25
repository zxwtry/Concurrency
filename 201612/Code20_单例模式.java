/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     四种单例模式
 */
public class Code20_单例模式 {
    //立即加载---饿汉模式
    static class Single1 {
        private static Single1 s = new Single1();
        private Single1() {super();}
        public static Single1 getInstance() {
            return s;
        }
    }
    //延迟加载---懒汉模式
    static class Single2 {
        private static Single2 s = null;
        private Single2() {super();}
        public synchronized static Single2 getInstance() {
            if (null == s) s = new Single2();
            return s;
        }
    }
    //延迟加载---懒汉模式 DCL双检查机制(Double-Check Locking)
    static class Single3 {
        private static Single3 s = null;
        private Single3() {super();}
        public static Single3 getInstance() {
            if (s == null) {
                synchronized (Single3.class) {
                    if (null == s) s = new Single3();
                }
            }
            return s;
        }
    }
    //延迟加载---静态内部类
    static class Single4 {
        private Single4() {super();}
        private static class Single4Handler {
            private static Single4 s = new Single4();
        }
        public static Single4 getInstance() {
            return Single4Handler.s;
        }
    }
    public static void main(String[] args) {
        Thread[] ts = new Thread[100];
        for (int i = 0; i < ts.length; i ++)
            new Thread() {
                @Override
                public void run() {
                    System.out.println(Single4.getInstance());
                };
            }.start();
    }
}
