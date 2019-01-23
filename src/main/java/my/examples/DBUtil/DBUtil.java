package my.examples.DBUtil;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DBUtil {
    // DB연결과 close를 하는 클래스
    private static final String driverClassname = "com.mysql.cj.jdbc.Driver"; // driver class이름도 JDBC Driver에 다르다.
    private static final String driverURL = "jdbc:mysql://localhost:3306/freeboard?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8"; // driver URL형식 Database마다 다르다.
    private static final String dbUserId = "supervisor";
    private static final String dbUserPassword = "7604kdy";

    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName(driverClassname);
            conn = DriverManager.getConnection(driverURL, dbUserId,dbUserPassword);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("DB연결을 할 수 없습니다.");
        }
        return conn;
    }
public static void close(PreparedStatement ps, Connection conn){

        try{ps.close();} catch(Exception ignore){}
        try{conn.close();} catch(Exception ignore){}
}
}
*/

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class DBUtil {
    private static HikariConfig config = null;
    private static DataSource ds = null;
    private static DBUtil instance = new DBUtil();

    private DBUtil() {
        String configFile = "/datasource.properties";
        HikariConfig config = new HikariConfig(configFile);

//        HikariConfig config = new HikariConfig();
//        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=UTF-8");
//        config.setUsername("connect");
//        config.setPassword("connect");
        ds = new HikariDataSource(config);
    }

    public static DBUtil getInstance() {
        return instance;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace(); // 로그를 남기는 코드가 있어야 한다.
            throw new RuntimeException("DB연결을 할 수 없습니다.");
        }
        return conn;
    }

    public static void rollback(Connection conn){
        try {
            conn.rollback();}catch(Exception ignore){

            }
        }
    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (Exception ignore) {
        }
    }

    public static void close(ResultSet rs,PreparedStatement ps) {
        try { rs.close();} catch (Exception ignore){}
        close(ps);
    }
    public static void close(PreparedStatement ps){
        try{ps.close();}catch(Exception ignore){}
    }
}