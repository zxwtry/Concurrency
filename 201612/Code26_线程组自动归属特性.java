/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     线程组自动归属特性
 * @details     执行结果：
 * @details         A处线程：main 所属的线程组名：main 中有线程组数量：0
 * @details         B处线程：main 所属的线程组名：main 中有线程组数量：1
 * @details         第一个线程组名称为：新的组
 */
public class Code26_线程组自动归属特性 {
    public static void main(String[] args) {
        //方法activeGroupCount()取得当前线程组对象中的子线程组数量
        //方法enumerate()的作用是线程组中的子线程组以复制的形式
        //拷贝到ThreadGroup[]数组对象中
        System.out.println("A处线程：" + Thread.currentThread().getName() + 
                " 所属的线程组名：" + Thread.currentThread().getThreadGroup().getName() + 
                " 中有线程组数量：" + Thread.currentThread().getThreadGroup().activeGroupCount());
        new ThreadGroup("新的组");//自动加到main组中
        System.out.println("B处线程：" + Thread.currentThread().getName() + 
                " 所属的线程组名：" + Thread.currentThread().getThreadGroup().getName() + 
                " 中有线程组数量：" + Thread.currentThread().getThreadGroup().activeGroupCount());
        ThreadGroup[] threadGroup = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        int threadGroupLength = Thread.currentThread().getThreadGroup().enumerate(threadGroup);
        if (threadGroupLength > 0)
            System.out.println("第一个线程组名称为：" + threadGroup[0].getName());
    }
}
