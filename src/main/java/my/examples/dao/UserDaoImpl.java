package my.examples.dao;


import my.examples.DBUtil.ConnectionContextHolder;
import my.examples.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao{
    private static UserDao instance = new UserDaoImpl();
    private UserDaoImpl(){}
    public static UserDao getInstance(){
        return instance;
    }

    @Override
    public void addUser(User user) {
        try{
            Connection conn = ConnectionContextHolder.getConnection();
            // nickname, email, passwd
            try(PreparedStatement ps = conn.prepareStatement(UserDAOSQL.INSERT);) {
                ps.setString(1, user.getNickname());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPasswd());
                ps.executeUpdate(); // 입력,수정,삭제 건수 가 리턴된다.
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
/*
    @Override
    public String getPasswdByEmail(String email) {
        String passwd = null; // return할 타입을 선언한다.

        Connection conn = ConnectionContextHolder.getConnection();
        try{

            try(PreparedStatement ps = conn.prepareStatement(UserDAOSQL.SELECT_BY_EMAIL);) {
                ps.setString(1, email);

                try(ResultSet rs = ps.executeQuery();){ // SELECT 문장을 실행, executeUpdate() - insert, update, delete

                    if (rs.next()) {
                        passwd = rs.getString(1);
                    }
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return passwd;
    }*/
    @Override
    public User getUserByEmail(String email) {
        User user = null; // return할 타입을 선언한다.

        Connection conn = ConnectionContextHolder.getConnection();
        try{

            try(PreparedStatement ps = conn.prepareStatement(UserDAOSQL.SELECT_BY_EMAIL);) {
                ps.setString(1, email);

                try(ResultSet rs = ps.executeQuery();){ // SELECT 문장을 실행, executeUpdate() - insert, update, delete

                    if (rs.next()) {
                        user = new User();
                        //user_id, nickname, email, passwd, regdate
                        user.setUser_id(rs.getLong(1));
                        user.setNickname(rs.getString(2));
                        user.setEmail(rs.getString(3));
                        user.setPasswd(rs.getString(4));
                        user.setRegdate(rs.getDate(5));
                    }
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return user;
    }
}
