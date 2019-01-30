package my.examples.service;

import my.examples.dto.Board;

import java.util.List;

public interface BoardService {
    //전체목록을 읽어어고 상세보기를 불러오고(+조회수증가) 삭제한다.
    public List<Board> getBoards(int page);
    public Board getBoard(Long post_id);
    public void deleteBoard(Long post_id);
    public void addBoard(Board board);
    public void modify(Board board);
    void addReBoard(Board board);



}
