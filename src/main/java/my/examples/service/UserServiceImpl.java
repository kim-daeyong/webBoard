package my.examples.service;


import my.examples.DBUtil.ConnectionContextHolder;
import my.examples.DBUtil.DBUtil;
import my.examples.dao.UserDao;
import my.examples.dao.UserDaoImpl;
import my.examples.dto.User;

import java.sql.Connection;

public class UserServiceImpl implements UserService{
    private static UserService instance = new UserServiceImpl();
    private UserServiceImpl(){}
    public static UserService getInstance(){
        return instance;
    }

    @Override
    public void addUser(User user) {
        Connection conn = null;
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);
            userDao.addUser(user);
            conn.commit(); // 트랜젝션 commit
        }catch(Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
    }
/*
    @Override
    public String getPasswdByEmail(String email) {
        String passwd = null;
        UserDao userDao = UserDaoImpl.getInstance();
        try(Connection conn = DBUtil.getInstance().getConnection();) {
            ConnectionContextHolder.setConnection(conn);
            passwd = userDao.getPasswdByEmail(email);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return passwd;
    }*/
    @Override
    public User getUserByEmail(String email) {
        User user = null;
        UserDao userDao = UserDaoImpl.getInstance();
        try(Connection conn = DBUtil.getInstance().getConnection();) {
            ConnectionContextHolder.setConnection(conn);
            user = userDao.getUserByEmail(email);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
}
