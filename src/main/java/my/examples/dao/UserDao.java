package my.examples.dao;


import my.examples.dto.User;

public interface UserDao {
    void addUser(User user);

    //String getPasswdByEmail(String email);

    User getUserByEmail(String email);
}
