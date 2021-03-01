package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Belmiro-Mungoi
 */
public class SingleConnection {

    private static String url = "jdbc:mysql://localhost:3306/projectoJsp";
    private static String user = "root";
    private static String password = "";
    private static String driver = "com.mysql.jdbc.Driver";
    private static Connection connection = null;

    static {
        connect();
    }

    public SingleConnection() {
        connect();
    }

    private static void connect() {
        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
