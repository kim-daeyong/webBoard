package my.examples.dao;

public class UserDAOSQL {
    public static final String INSERT = "INSERT INTO freeboard.user (nickname, email, passwd) VALUES (?, ?, ?)";
    public static final String SELECT_BY_EMAIL = "SELECT user_id, nickname, email, passwd, regdate FROM freeboard.user WHERE email = ?";
}
