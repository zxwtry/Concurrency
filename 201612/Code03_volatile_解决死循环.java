/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @date        2016年12月19日 下午1:17:38
 * @details     private volatile boolean ip = true;时：(退出VM)
 * @details         在main中我要停止线程
 * @details         run print threadName=A
 * @details         setIP前
 * @details         setIP后
 * @details         end print threadName=A
 * @details     
 * @details     private boolean ip = true;时：(死循环)
 * @details         在main中我要停止线程
 * @details         run print threadName=A
 * @details         setIP前
 * @details         setIP后
 * @details     
 */
public class Code03_volatile_解决死循环 {
    static class Print implements Runnable{
//      private volatile boolean ip = true;
        private boolean ip = true;
        public boolean ip() {
            return ip;
        }
        public void print() {
            System.out.println("run print threadName=" + Thread.currentThread().getName());
            while (this.ip) {}
            System.out.println("end print threadName=" + Thread.currentThread().getName());
        }
        public void setIP(boolean ip) {
            this.ip = ip;
        }
        @Override
        public void run() {
            print();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Print p = new Print();
        new Thread(p, "A").start();
        System.out.println("在main中我要停止线程");
        Thread.sleep(1000);
        System.out.println("setIP前");
        p.setIP(false);
        System.out.println("setIP后");
    }
}

