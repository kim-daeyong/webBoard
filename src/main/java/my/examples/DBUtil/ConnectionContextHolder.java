package my.examples.DBUtil;

import java.sql.Connection;

public class ConnectionContextHolder { // ThreadLocal을 위한 클래스
    private static ThreadLocal<Connection> threadLocal
            = new ThreadLocal<>();

    public static void setConnection(Connection connection){
        threadLocal.set(connection);
    }

    public static Connection getConnection() {
        return threadLocal.get();
    }

}
