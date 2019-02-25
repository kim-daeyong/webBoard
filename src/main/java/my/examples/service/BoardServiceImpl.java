package my.examples.service;

import my.examples.DBUtil.ConnectionContextHolder;
import my.examples.DBUtil.DBUtil;
import my.examples.dao.BoardDao;
import my.examples.dao.BoardDaoImpl;
import my.examples.dto.Board;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BoardServiceImpl implements  BoardService {

    public static final int SIZE = 5;

    @Override
    public List<Board> getBoards(int page) {
        BoardDao boardDao = new BoardDaoImpl();
        int start = page * SIZE -SIZE;
        int limit = SIZE;

        //Connection conn = null;
        List<Board> boards = new ArrayList<>();
        // try resource로 바꿔줬다.

        /*
        try {
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);
            boards = boardDao.getBoards(start, limit);
            conn.commit(); // 트랜젝션 commit
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }*/
        try(Connection conn = DBUtil.getInstance().getConnection();) {
            ConnectionContextHolder.setConnection(conn);
            boards = boardDao.getBoards(start, limit);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return boards;
    }

    @Override
    public Board getBoard(Long post_id) {
        Board board = null;
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try{
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);

            board = boardDao.getBoard(post_id);
            boardDao.updateReadCount(post_id);
            conn.commit(); // 트랜젝션 commit
        }catch(Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally{
            DBUtil.close(conn);
        }
        return board;
    }

    @Override
    public void deleteBoard(Long post_id) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try {
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);
            boardDao.deleteBoard(post_id);
            conn.commit(); // 트랜젝션 commit
        }catch(Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public void addBoard(Board board) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try {
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);

            boardDao.addBoard(board);
            Long lastInsertId = boardDao.getLastInsertId();
            boardDao.updateLastInsertId(lastInsertId);

            conn.commit(); // 트랜젝션 commit
        }catch(Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }

    }

    @Override
    public void modify(Board board) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try {
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);
            boardDao.modify(board);
            conn.commit(); // 트랜젝션 commit
        }catch(Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }

    }
    @Override
    public void addReBoard(Board board) {
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try {
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);

            Board oBoard = boardDao.getBoard(board.getPost_id());
            board.setFam_seq(oBoard.getFam_seq());
            board.setFam_num(oBoard.getFam_num());
            board.setFam_lev(oBoard.getFam_lev());
            boardDao.updateGroupSeqGt(oBoard.getFam_num(), oBoard.getFam_seq());
            boardDao.addReBoard(board);

            conn.commit(); // 트랜젝션 commit
        }catch(Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
    }
}
