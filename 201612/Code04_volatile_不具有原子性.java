/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @date        2016年12月19日 下午4:07:53
 * @details     无论是++ count还是count ++
 * @details     最终输出经常不是10000
 * @details     最终输出小于10000
 */
public class Code04_volatile_不具有原子性 {
    static class MyThread extends Thread {
        public static volatile int count = 0;
        public static void add() {
            for (int i = 0; i < 100; i ++)
//              ++ count;
                count ++;
        }
        @Override
        public void run() {
            MyThread.add();
        }
    }
    public static void main(String[] args) {
        MyThread[] mt = new MyThread[100]; 
        for (int i = 0; i < mt.length; i ++) {
            mt[i] = new MyThread();
        }
        for (int i = 0; i < mt.length; i ++) {
            mt[i].start();
        }
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(MyThread.count);
    }
}
