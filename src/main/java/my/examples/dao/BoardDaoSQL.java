package my.examples.dao;

public class BoardDaoSQL {
    public static final String SELECT_BY_ID =
            "select post_id,title,nickname,regdate,content,view from freeboard.freepost where post_id = ?";
    public static final String SELECT_BY_PAGING =
            "select post_id,title,nickname,regdate,content,view from freeboard.freepost ORDER BY post_id desc limit ?, ?";
    public static final String INSERT =
            "INSERT INTO freeboard.freepost(nickname, content, title) VALUES(?, ?, ?)";
    public static final String UPDATECOUNT =
            "UPDATE freepost SET view = view + 1 WHERE post_id = ?";
    public static final String DELETE =
            "DELETE FROM freepost WHERE post_id = ?";
    public static final String MODIFY =
            "UPDATE freepost SET title = ?, content = ? WHERE post_id = ?";
}
