
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     部分执行结果：
 * @details         正常执行了58970
 * @details         正常执行了58973
 * @details         正常执行了58977
 * @details         正常执行了58981
 * @details         正常执行了58989
 * @details         正常执行了58993
 * @details    cancel()仍会执行的原因是： 
 * @details         有时cancle有时没有争抢到queue锁 
 */
public class Code19_cancel之后有时还会执行 {
    static AtomicInteger i = new AtomicInteger(0);
    static class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("正常执行了" + i.get());
        }
    }
    public static void main(String[] args)  {
        while (true) {
            try {
                i.incrementAndGet();
                Timer timer = new Timer();
                MyTask task = new MyTask();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = "2016-12-25 14:37:30";
                timer.schedule(task, sdf.parse(dateStr));
                timer.cancel();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
