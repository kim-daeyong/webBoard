package my.examples.dto;

import java.util.Date;

// VO or DTO - 하나의 값을 가지는 객체
public class Board {
    private Long post_id;
    private String title;
    private String content;
    private String nickname;
    private Date regdate;
    private long view;
    private int fam_num;
    private int fam_lev;
    private int fam_seq;



    public Board(String title, String content, Long post_id){
        this.post_id = post_id;
        this.title = title;
        this.content = content;

    }

    public Board() {
        this.regdate = new Date();
        this.view = 0;
    }
    public Board(String title, String content, String nickname) {
        this();
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }

    public Board(Long post_id, String title, String content, String nickname, Date regdate, long view) {
        this(title, content, nickname);
        this.post_id = post_id;
        this.regdate = regdate;
        this.view = view;
    }
    public Board(Long post_id, String title, String content, String nickname, Date regdate, long view,int fam_num,int fam_lev,int fam_seq) {
        this(post_id, title, content, nickname,regdate,view);
        this.fam_num = fam_num;
        this.fam_lev = fam_lev;
        this.fam_seq = fam_seq;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public int getFam_num() {
        return fam_num;
    }

    public void setFam_num(int fam_num) {
        this.fam_num = fam_num;
    }

    public int getFam_lev() {
        return fam_lev;
    }

    public void setFam_lev(int fam_lev) {
        this.fam_lev = fam_lev;
    }

    public int getFam_seq() {
        return fam_seq;
    }

    public void setFam_seq(int fam_seq) {
        this.fam_seq = fam_seq;
    }

    @Override
    public String toString() {
        return "Board{" +
                "post_id=" + post_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", nickname='" + nickname + '\'' +
                ", regdate=" + regdate +
                ", view=" + view +
                ", fam_num=" + fam_num +
                ", fam_lev=" + fam_lev +
                ", fam_seq=" + fam_seq +
                '}';
    }
}
