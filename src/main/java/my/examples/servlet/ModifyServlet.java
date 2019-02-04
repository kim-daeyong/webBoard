package my.examples.servlet;


import my.examples.dto.Board;
import my.examples.service.BoardService;
import my.examples.service.BoardServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyServlet", urlPatterns = "/modify")
public class ModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long post_id = 0L;
        String idStr = req.getParameter("post_id");
        try {
            post_id = Long.parseLong(idStr);
    } catch (Exception ex) {
        return;
    }

    BoardService boardService = new BoardServiceImpl();
    Board board = boardService.getBoard(post_id);

        req.setAttribute("board", board);
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/modify.jsp");
        requestDispatcher.forward(req, resp);
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        long post_id = 0L;
        String title = null;
        String content = null;
        try {
            String idStr = req.getParameter("post_id");
            title = req.getParameter("title");
            content = req.getParameter("content");
            post_id = Long.parseLong(idStr);

        } catch (Exception ex) { }

        BoardService boardService = new BoardServiceImpl();
        Board board = new Board(title, content, post_id);
        boardService.modify(board);

        resp.sendRedirect("/freeboard");
    }

}

