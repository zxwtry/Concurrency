import java.util.concurrent.locks.ReentrantLock;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     getHoldCount---不是Lock的方法
 * @details     getHoldCount---是ReentrantLock的方法
 * @details     程序运行结果：
 * @details         ServiceMethod1 getHoldCount=1
 * @details         ServiceMethod2 getHoldCount=2
 */
public class Code17_ReentrantLock_getHoldCount {
    static class Service {
        private ReentrantLock l = new ReentrantLock();
        public void serviceMethod1() {
            try {
                l.lock();
                System.out.println("ServiceMethod1 getHoldCount="+l.getHoldCount());
                serviceMethod2();
            } finally {
                l.unlock();
            }
        }
        public void serviceMethod2() {
            try {
                l.lock();
                System.out.println("ServiceMethod2 getHoldCount="+l.getHoldCount());
            } finally {
                 l.unlock();
            }
        }
    }
    public static void main(String[] args) {
        Service service = new Service();
        service.serviceMethod1();
    }
}
