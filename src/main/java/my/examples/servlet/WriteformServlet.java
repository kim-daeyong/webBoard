package my.examples.servlet;

import my.examples.dao.BoardDao;
import my.examples.dao.BoardDaoImpl;
import my.examples.dto.Board;
import my.examples.dto.User;
import my.examples.service.BoardService;
import my.examples.service.BoardServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WriteServlet", urlPatterns = "/write")
public class WriteformServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/write.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*2
        req.setCharacterEncoding("UTF-8");
        String nickname = req.getParameter("nickname");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
/*1
        BoardDao boardDao = new BoardDaoImpl();
        Board board = new Board(userid, title, contents);
        boardDao.addBoard(board);
        *//*
        Board board = new Board(nickname, title, content);
        BoardService boardService = new BoardServiceImpl();
        boardService.addBoard(board);
        resp.sendRedirect("/freeboard");
    } */

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        User user = (User)req.getSession().getAttribute("logininfo");


        BoardService boardService = new BoardServiceImpl();
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);;
        board.setNickname(user.getNickname());
        boardService.addBoard(board);
        resp.sendRedirect("/freeboard");

    }
}
