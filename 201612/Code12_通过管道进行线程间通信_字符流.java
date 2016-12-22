import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     连接代码：
 * @details         out.connect(in);
 * @details         in.connect(out);
 * @details         (二者选一，不能同时存在)
 * @details     执行之后的输出如下：
 * @details         read: 
 * @details         write: 
 * @details         123456789101112131415161718192021222324252627282930
 * @details         123456789101112131415161718192021222324252627282930
 */
public class Code12_通过管道进行线程间通信_字符流 {
    static class WriteData {
        public void writeMethod(PipedOutputStream out) {
            try {
                System.out.println("write: ");
                for (int i = 0; i < 30; i ++) {
                    String outDate = "" + (i + 1);
                    out.write(outDate.getBytes());
                    System.out.print(outDate);
                }
                System.out.println();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class ReadData {
        public void readMethod(PipedInputStream in) {
            try {
                System.out.println("read: ");
                byte[] byteArray = new byte[20];
                int readLength = in.read(byteArray);
                while (readLength != -1) {
                    String inData = new String(byteArray, 0, readLength);
                    System.out.print(inData);
                    readLength = in.read(byteArray);
                }
                System.out.println();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadWrite extends Thread {
        PipedOutputStream out = null;
        WriteData write = null;
        public ThreadWrite(PipedOutputStream out, WriteData write) {
            this.out = out;
            this.write = write;
        }
        @Override
        public void run() {
            write.writeMethod(out);
        }
    }
    static class ThreadRead extends Thread {
        PipedInputStream in = null;
        ReadData read = null;
        public ThreadRead(PipedInputStream in, ReadData read) {
            this.in = in;
            this.read = read;
        }
        @Override
        public void run() {
            read.readMethod(in);
        }
    }
    public static void main(String[] args) throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        WriteData write = new WriteData();
        ReadData read = new ReadData();
        out.connect(in);
//      in.connect(out);
        Thread tr = new ThreadRead(in, read);
        Thread tw = new ThreadWrite(out, write);
        tr.start();
        Thread.sleep(1000);
        tw.start();
    }
}
