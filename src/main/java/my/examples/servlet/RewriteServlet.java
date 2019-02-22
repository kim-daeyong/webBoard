package my.examples.servlet;


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

@WebServlet(name = "RewriteServlet", urlPatterns = "/rewrite")
public class RewriteServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            long post_id = 0;
            String idStr = req.getParameter("post_id");

            try{
                post_id = Long.parseLong(idStr);
            }catch(Exception ex){
                ex.printStackTrace();
                return;
            }

            BoardService boardService = new BoardServiceImpl();
            Board board = boardService.getBoard(post_id);

            String content = board.getContent();
            content = content.replaceAll("\n", "\n> ");
            System.out.println(content);
            board.setContent(content);

            req.setAttribute("board", board);

            RequestDispatcher requestDispatcher =
                    req.getRequestDispatcher("/WEB-INF/views/rewrite.jsp");
            requestDispatcher.forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            long post_id = 0L;
            try{
                String idStr = req.getParameter("post_id");
                post_id = Long.parseLong(idStr);

            }catch(Exception ex){
                ex.printStackTrace();
                return;
            }

            String title = req.getParameter("title");
            String content = req.getParameter("content");


            User user = (User) req.getSession().getAttribute("logininfo");

            BoardService boardService = new BoardServiceImpl();
            Board board = new Board();
            board.setPost_id(post_id);
            board.setTitle(title);
            board.setContent(content);
            board.setNickname(user.getNickname());
            boardService.addReBoard(board);
            resp.sendRedirect("/freeboard");
        }
}
