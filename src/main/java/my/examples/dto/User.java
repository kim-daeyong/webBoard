package my.examples.dto;

import java.util.Date;

public class User {
    private Long user_id;
    private String nickname;
    private String email;
    private String passwd;
    private Date regdate;

    public User() {
    }

    public User(String nickname, String email, String passwd) {

        this.nickname = nickname;
        this.email = email;
        this.passwd = passwd;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}
/*
CREATE TABLE user(
 id BIGINT AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL,
 nickname  VARCHAR(255) NOT NULL UNIQUE,
 email VARCHAR(255) NOT NULL UNIQUE,
 passwd VARCHAR(255) NOT NULL,
 regdate DATETIME NOT NULL DEFAULT     CURRENT_TIMESTAMP,
PRIMARY KEY(id)
);

 */