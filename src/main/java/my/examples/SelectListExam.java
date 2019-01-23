package my.examples;

import my.examples.dao.BoardDao;
import my.examples.dao.BoardDaoImpl;
import my.examples.dto.Board;

import java.util.List;

public class SelectListExam {
    public static void main(String[] args){
        /*
        final String driverClassname = "com.mysql.cj.jdbc.Driver"; // driver class이름도 JDBC Driver에 다르다.
        final String driverURL = "jdbc:mysql://localhost:3306/freeboard?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8"; // driver URL형식 Database마다 다르다.
        final String dbUserId = "supervisor";
        final String dbUserPassword = "7604kdy";

        long start = 0;
        int limit = 3;

        //long idParam = 1;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // a. DB 연결 - Connection
            //    DB연결을 하려면 필요한 정보가 있다. Driver classname, DB URL, DB UserId , DB User Password
            Class.forName(driverClassname); // driver class가 로딩.
            conn = DriverManager.getConnection(driverURL, dbUserId, dbUserPassword);
            if(conn != null) {
                System.out.println("conn ok!");
                System.out.println(conn.getClass().getName());
            }
            // b. SELECT SQL 준비 - Connection
            String sql = "select id,title,userid,regdate,contents,view from freepost ORDER BY id = ?, ?";
            ps = conn.prepareStatement(sql);
            // c. 바인딩 - PreparedStatement
            //ps.setLong(1, idParam); // 첫번째 물음표에 1를 바인딩한다.
            ps.setLong(1, start);
            ps.setInt(2, limit);

            // d. SQL 실행 - PreparedStatement
            rs = ps.executeQuery(); // SELECT 문장을 실행, executeUpdate() - insert, update, delete

            // e. 1건의 row를 읽어온다. row는 여러개의 컬럼으로 구성되어 있다. - ResultSet
            // f. e에서 읽어오지 못하는 경우도 있다.

            while(rs.next()){
                long id = rs.getLong(1);
                String title = rs.getString(2);
                String contents = rs.getString(5);
                String userid = rs.getString(3);
                Date regdate = rs.getDate(4);
                int view = rs.getInt(6);

                System.out.println(id + "," + title + ", " + contents + ", " + userid + ", " + regdate + ", " + view);
            }


        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            // g. ResultSet, PreparedStatement, Connection 순으로 close를 한다.
            try{ rs.close(); } catch(Exception ignore){}
            try{ ps.close(); } catch(Exception ignore){}
            try{ conn.close(); } catch(Exception ignore){}
        }*/
        BoardDao boardDao = new BoardDaoImpl();
        List<Board> boards = boardDao.getBoards(0, 3);
        for(Board board : boards) {
            System.out.println(board);
        }

    }
}
