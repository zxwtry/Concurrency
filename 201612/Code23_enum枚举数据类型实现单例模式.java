import java.sql.Connection;
import java.sql.DriverManager;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     必须添加mysql-connector-java-***.jar到lib
 * @details     使用枚举类时，构造方法会被自动调用。
 */
public class Code23_enum枚举数据类型实现单例模式 {
    static enum GetConnection {
        connectionFactory;
        private Connection connection;
        private GetConnection() {
            try {
                System.out.println("调用了GetConnection的构造");
                String url = "jdbc:mysql://127.0.0.1:3306/xxx";
                String user = "root";
                String password = "";
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public Connection getConnection() {
            return connection;
        }
    }
    public static void main(String[] args) {
        Thread[] ts = new Thread[100];
        for (int i = 0; i < ts.length; i ++)
            ts[i] = new Thread() {
                @Override
                public void run() {
                    System.out.println(GetConnection.connectionFactory.getConnection());
                }
            };
        for (Thread t : ts)
            t.start();
    }
}
