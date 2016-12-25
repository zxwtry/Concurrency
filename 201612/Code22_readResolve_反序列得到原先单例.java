import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     序列化和反序列化
 */
public class Code22_readResolve_反序列得到原先单例 {
    //立即加载---饿汉模式
    static class Single1 implements Serializable {
        private static final long serialVersionUID = 9908L;
        private static Single1 s = new Single1();
        private Single1() {super();}
        public static Single1 getInstance() {
            return s;
        }
        protected Object readResolve() throws ObjectStreamException {
            return s;
        }
    }
    //延迟加载---懒汉模式
    static class Single2 implements Serializable {
        private static final long serialVersionUID = 9909L;
        private static Single2 s = null;
        private Single2() {super();}
        public synchronized static Single2 getInstance() {
            if (null == s) s = new Single2();
            return s;
        }
        protected Object readResolve() throws ObjectStreamException {
            return s;
        }
    }
    //延迟加载---懒汉模式 DCL双检查机制(Double-Check Locking)
    static class Single3 implements Serializable {
        private static final long serialVersionUID = 9910L;
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
        protected Object readResolve() throws ObjectStreamException {
            return s;
        }
    }
    //延迟加载---静态内部类
    static class Single4 implements Serializable {
        private static final long serialVersionUID = 9911L;
        private Single4() {super();}
        private static class Single4Handler {
            private static Single4 s = new Single4();
        }
        public static Single4 getInstance() {
            return Single4Handler.s;
        }
        protected Object readResolve() throws ObjectStreamException {
            return Single4Handler.s;
        }
    }
    public static void main(String[] args) throws Exception {
        Single4 s4 = Single4.getInstance();
        String fileName = "s4";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
        oos.writeObject(s4);
        oos.close();
        Thread.sleep(100);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)));
        Single4 s4c = (Single4)ois.readObject();
        ois.close();
        System.out.println(s4);
        System.out.println(s4c);
    }
}
