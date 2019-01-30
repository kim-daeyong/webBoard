package my.examples.dao;

import my.examples.dto.Board;

import java.util.List;

public interface BoardDao {
    public Board getBoard(Long post_id);
    public List<Board> getBoards(int start, int limit);
    void addBoard(Board board);

    void deleteBoard(Long post_id);

    void updateReadCount(long post_id);

    void modify(Board board);

    Long getLastInsertId();

    void updateLastInsertId(Long post_id);

    void updateGroupSeqGt(int fam_num, int fam_seq);

    void addReBoard(Board board);
}
