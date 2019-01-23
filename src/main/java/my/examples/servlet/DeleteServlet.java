package my.examples.servlet;


import my.examples.service.BoardService;
import my.examples.service.BoardServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long post_id = 0L;
        try{
            String idStr = req.getParameter("post_id");
            post_id = Long.parseLong(idStr);
        }catch(Exception ex){
            // id가 잘못되었을 경우엔 에러페이지로 이동.
        }
        BoardService boardService = new BoardServiceImpl();
        boardService.deleteBoard(post_id);

        resp.sendRedirect("/freeboard");
    }
}