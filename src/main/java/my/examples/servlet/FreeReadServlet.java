package my.examples.servlet;

import my.examples.dao.BoardDao;
import my.examples.dao.BoardDaoImpl;
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

@WebServlet(name = "FreeReadServlet", urlPatterns = "/read")
public class FreeReadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long post_id = 0L;
        String idStr = req.getParameter("post_id");
        try{
            post_id = Long.parseLong(idStr);
        }catch(Exception ex){
            // 오류 화면으로 redirect
            return;
        }
        /*BoardDao boardDao = new BoardDaoImpl();
        Board board = boardDao.getBoard(id);
        if(board == null){
            // 오류 화면으로 redirect
            return;
        }

        //boardDao.updateReadCount(id);*/
        BoardService boardService = new BoardServiceImpl();
        Board board = boardService.getBoard(post_id);

        req.setAttribute("board", board);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/read.jsp");
        requestDispatcher.forward(req, resp);
    }
}

