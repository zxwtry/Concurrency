/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     线程组自动归属特性
 * @details         就是在当前main里面新建一个ThreadGroup tg
 * @details         tg的parent就是main线程组
 * @details         还有main线程组的parent是system线程组
 * @details     
 * @details     组内的线程批量停止
 * @details     执行结果：
 * @details         ThreadName=线程1准备开始执行死循环
 * @details         ThreadName=线程4准备开始执行死循环
 * @details         ThreadName=线程5准备开始执行死循环
 * @details         ThreadName=线程3准备开始执行死循环
 * @details         ThreadName=线程2准备开始执行死循环
 * @details         调用了interrupt()方法
 * @details         ThreadName=线程4结束了
 * @details         ThreadName=线程5结束了
 * @details         ThreadName=线程1结束了
 * @details         ThreadName=线程2结束了
 * @details         ThreadName=线程3结束了
 * @details     如果把while (! this.isInterrupted())里面的sleep取消注释，
 * @details     那么会出现InterruptedException。
 */
public class Code27_线程默认归属和线程批量停止 {
    public static void main(String[] args) throws Exception {
//      System.out.println("线程：" + Thread.currentThread().getName() + " 所在的线程组名为：" + Thread.currentThread().getThreadGroup().getName());
//      ThreadGroup group = new ThreadGroup("新的组");
//      System.out.println(group.getParent().getName());        //输出main
//      System.out.println(group.getParent().getParent().getName());    //system
//      System.out.println(group.getParent().getParent().getParent().getName());    //抛出空指针
        
        //线程组内的线程批量停止
        bashStop();
    }
    
    static void bashStop() throws Exception {
        ThreadGroup group = new ThreadGroup("批量停止的线程组");
        for (int i = 0; i < 5; i ++) {
            MyThread thread = new MyThread(group, "线程" + (i+1));
            thread.start();
        }
        Thread.sleep(5000);
        group.interrupt();
        System.out.println("调用了interrupt()方法");
    }
    
    static class MyThread extends Thread {
        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }
        @Override
        public void run() {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + "准备开始执行死循环");
            while (! this.isInterrupted()) {
//              try {
//                  Thread.sleep(100);
//              } catch (InterruptedException e) {
//                  e.printStackTrace();
//              }
            }
            System.out.println("ThreadName=" + Thread.currentThread().getName() + "结束了");
        }
    }
}
