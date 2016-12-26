/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     执行结果：
 * @details         Run Method!
 * @details         main线程中有多少个子线程组：1 名字为：A
 * @details         此线程组及其子组中所有活动线程复制到指定数组，返回放入数组中线程数：1
 * @details         此线程组及其子组中所有活动线程复制到指定数组，返回放入数组中线程数：1
 * @details         此线程组及其子组中所有活动线程复制到指定数组，返回放入数组中线程数：0
 * @details         此线程组及其子组中所有活动线程复制到指定数组，返回放入数组中线程数：0
 */
public class Code25_线程对象关联线程组_多级关联 {
    public static void main(String[] args) throws Exception {
        //在main组中添加一个线程组A，然后在这个A组中添加线程对象Z
        //方法activeGroupCount()和activeCount()的值不是固定值
        //是系统中环境的一个快照
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group = new ThreadGroup(mainGroup, "A");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Run Method!");
                    Thread.sleep(2000);//线程必须在运行状态才可以受组管理
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread newThread = new Thread(group, runnable);
        newThread.setName("Z");
        newThread.start();//线程必须启动然后才归到组A中
        ThreadGroup[] listGroup = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(listGroup);
        System.out.println("main线程中有多少个子线程组：" + listGroup.length + " 名字为：" + listGroup[0].getName());
        while (true) {
            Thread[] listThread = new Thread[listGroup[0].activeCount()];
            System.out.println("此线程组及其子组中所有活动线程复制到指定数组，返回放入数组中线程数：" + listGroup[0].enumerate(listThread));;
            if (listThread.length == 0) break;
            Thread.sleep(1000);
        }
    }
}
