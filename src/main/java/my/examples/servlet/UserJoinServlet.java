package my.examples.servlet;


import my.examples.dto.User;
import my.examples.service.UserService;
import my.examples.service.UserServiceImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserJoinServlet", urlPatterns = "/join")
public class UserJoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        if(error != null) {
            req.setAttribute("error", "값을 알맞게 입력하세요.");
        }
        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/joinform.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nickname = req.getParameter("nickname");
        String email = req.getParameter("email");
        String passwd1 = req.getParameter("passwd1");
        String passwd2 = req.getParameter("passwd2");

        // 실제 값을 검사
        if(nickname != null && nickname.length() < 2){
            resp.sendRedirect("/join?error=babo");
            return;
        }
        // 암호1과 암호2가 같으냐.
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // 암호화 하는 코드
        String encodePasswd = passwordEncoder.encode(passwd1);

        User user = new User(nickname, email, encodePasswd);
        UserService userService = UserServiceImpl.getInstance();
        userService.addUser(user);

        resp.sendRedirect("/freeboard");
    }
}
