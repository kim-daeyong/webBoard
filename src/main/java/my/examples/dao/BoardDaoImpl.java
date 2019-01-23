package my.examples.dao;

import my.examples.DBUtil.ConnectionContextHolder;
import my.examples.DBUtil.DBUtil;
import my.examples.dto.Board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {
    @Override
    public Board getBoard(Long post_id) {

        Board board = null;

        Connection conn = ConnectionContextHolder.getConnection();
        // Connection conn = null;
        //PreparedStatement ps = null;
        //ResultSet rs = null;

        try {
            //conn = DBUtil.getInstance().getConnection();
            //conn = ConnectionContextHolder.getConnection();
            // a. DB 연결 - Connection
            //    DB연결을 하려면 필요한 정보가 있다. Driver classname, DB URL, DB UserId , DB User Password
            // driver class가 로딩.
            // b. SELECT SQL 준비 - Connection

            /*String sql = "select id,title,userid,regdate,contents,view from freeboard.freepost where id = ?";
            ps = conn.prepareStatement(sql);*/
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.SELECT_BY_ID);) {
            // c. 바인딩 - PreparedStatement
            //ps.setLong(1, idParam); // 첫번째 물음표에 1를 바인딩한다.
            ps.setLong(1, post_id);

            // d. SQL 실행 - PreparedStatement
                try(ResultSet rs = ps.executeQuery();) { // SELECT 문장을 실행, executeUpdate() - insert, update, delete

                    ////rs = ps.executeQuery(); // SELECT 문장을 실행, executeUpdate() - insert, update, delete

                    // e. 1건의 row를 읽어온다. row는 여러개의 컬럼으로 구성되어 있다. - ResultSet
                    // f. e에서 읽어오지 못하는 경우도 있다.

                    if (rs.next()) {
                        post_id = rs.getLong(1);
                        String title = rs.getString(2);
                        String nickname = rs.getString(3);
                        Date regdate = rs.getDate(4);
                        String content = rs.getString(5);
                        long view = rs.getLong(6);

                        System.out.println(post_id + "," + title + ", " + content + ", " + nickname + ", " + regdate + ", " + view);

                        board = new Board(post_id, title, content, nickname, regdate, view);
                        System.out.println("board: " + board);
                    } else {
                        System.out.println(post_id + "에 해당하는 값이 없습니다.");
                    }
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }/*finally {
            // g. ResultSet, PreparedStatement, Connection 순으로 close를 한다.
            //try{ rs.close(); } catch(Exception ignore){}
            //try{ ps.close(); } catch(Exception ignore){}
            //try{ conn.close(); } catch(Exception ignore){}
            DBUtil.close(rs, ps);
        }*/
        return board;
    }

    @Override
    public List<Board> getBoards(int start, int limit) {
        Board board = null;
        List<Board> boards = new ArrayList<>();
        //long idParam = 1;

        //Connection conn = null;
        //PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            Connection conn = ConnectionContextHolder.getConnection();
            //conn = DBUtil.getInstance().getConnection();
            //conn = ConnectionContextHolder.getConnection();
            // a. DB 연결 - Connection
            //    DB연결을 하려면 필요한 정보가 있다. Driver classname, DB URL, DB UserId , DB User Password
            // driver class가 로딩.
/*
            if(conn != null) {
                System.out.println("conn ok!");
                System.out.println(conn.getClass().getName());
            }*/
            // b. SELECT SQL 준비 - Connection
            try (PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.SELECT_BY_PAGING);) {
            /*
            String sql = "select id,title,userid,regdate,contents,view from freeboard.freepost ORDER BY id desc limit ?, ?";
            ps = conn.prepareStatement(sql);
            */

                // c. 바인딩 - PreparedStatement
                //ps.setLong(1, idParam); // 첫번째 물음표에 1를 바인딩한다.
                ps.setLong(1, start);
                ps.setInt(2, limit);

                // d. SQL 실행 - PreparedStatement
                try (ResultSet rs = ps.executeQuery();) { // SELECT 문장을 실행, executeUpdate() - insert, update, delet
                    ////rs = ps.executeQuery(); // SELECT 문장을 실행, executeUpdate() - insert, update, delete

                    // e. 1건의 row를 읽어온다. row는 여러개의 컬럼으로 구성되어 있다. - ResultSet
                    // f. e에서 읽어오지 못하는 경우도 있다.

                    while (rs.next()) {
                        long post_id = rs.getLong(1);
                        String title = rs.getString(2);
                        String nickname = rs.getString(3);
                        Date regdate = rs.getDate(4);
                        String content = rs.getString(5);
                        long view = rs.getLong(6);

                        System.out.println(post_id + "," + title + ", " + content + ", " + nickname + ", " + regdate + ", " + view);
                        board = new Board(post_id, title, content, nickname, regdate, view);
                        boards.add(board);
                        }
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();


            } /*finally {
            // g. ResultSet, PreparedStatement, Connection 순으로 close를 한다.
            //try{ rs.close(); } catch(Exception ignore){}
            //try{ ps.close(); } catch(Exception ignore){}
            //try{ conn.close(); } catch(Exception ignore){}
            DBUtil.close(rs, ps);
        }*/
            return boards;
        }

    @Override
    public void addBoard(Board board) {
        //Connection conn = null;
        //PreparedStatement ps = null;
        try{
            //conn = DBUtil.getInstance().getConnection();
            //conn = ConnectionContextHolder.getConnection();
            Connection conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.INSERT);) {
            /*
            String sql = "INSERT INTO freeboard.freepost(userid, contents, title) VALUES(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            */
                ps.setString(1, board.getNickname());
                ps.setString(2, board.getContent());
                ps.setString(3, board.getTitle());
                ps.executeUpdate(); // 입력,수정,삭제 건수 가 리턴된다.
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }/*finally {
           // DBUtil.close(ps, conn);
            DBUtil.close(ps);
        }*/
    }

    @Override
    public void deleteBoard(Long post_id) {
        //Connection conn = null;
        //PreparedStatement ps = null;
        try{
            //conn = DBUtil.getInstance().getConnection();
            //conn = ConnectionContextHolder.getConnection();
            Connection conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.DELETE);) {
            /*
            String sql = "DELETE FROM freepost WHERE id = ?";
            ps = conn.prepareStatement(sql);
            */
                ps.setLong(1, post_id);
                ps.executeUpdate(); // 입력,수정,삭제 건수 가 리턴된다.
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }/*inally {
            //DBUtil.close(ps, conn);
            DBUtil.close(ps);
        }*/
    }

    @Override
    public void updateReadCount(long post_id) {
        //Connection conn = null;
        //PreparedStatement ps = null;
        try{
            //conn = DBUtil.getInstance().getConnection();
            //conn = ConnectionContextHolder.getConnection();
            Connection conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATECOUNT);) {
            /*
            String sql = "UPDATE freepost SET view = view + 1 WHERE id = ?";
            ps = conn.prepareStatement(sql);*/
                ps.setLong(1, post_id);
                ps.executeUpdate(); // 입력,수정,삭제 건수 가 리턴된다.
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }/*finally {
            //DBUtil.close(ps, conn);
            DBUtil.close(ps);
        }*/
    }

    @Override
    public void modify(Board board) {

        try{
            Connection conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.MODIFY);) {
                ps.setString(1, board.getTitle());
                ps.setString(2, board.getContent());
                ps.setLong(3, board.getPost_id());
                ps.executeUpdate(); // 입력,수정,삭제 건수 가 리턴된다.
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
