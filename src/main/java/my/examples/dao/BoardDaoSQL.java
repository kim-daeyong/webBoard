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
    public static final String UPDATE_GROUP_SEQ_GT =
            "update board set fam_seq = fam_seq + 1 where fam_num = ? and fam_seq > ?";
    public static final String INSERT_RE =
            "insert into board (title, user_id, nickname, content, fam_num, fam_seq, fam_lev) " +
                    "values( ?, ?, ?, ? ,  ? , ?, ? )";
    public static final String SELECT_LAST_INSERT_ID =
            "select LAST_INSERT_ID()";
    public static final String UPDATE_LAST_INSERT_ID =
            "update board set fam_num = ? where post_id = ?";
}
