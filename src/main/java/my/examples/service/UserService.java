package my.examples.service;

import my.examples.dto.User;

public interface UserService {
    void addUser(User user);

    //String getPasswdByEmail(String email);

    User getUserByEmail(String email);
}
