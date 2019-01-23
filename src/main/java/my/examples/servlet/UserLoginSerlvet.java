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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserLoginSerlvet", urlPatterns = "/login")
public class UserLoginSerlvet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/loginform.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");

        UserService userService = UserServiceImpl.getInstance();
        //String encodePasswd = userService.getPasswdByEmail(email);
       // String encodePasswd = userService.getUserByEmail(email);
        //        if(encodePasswd != null){
//            PasswordEncoder passwordEncoder =
//                    PasswordEncoderFactories.createDelegatingPasswordEncoder();
//            boolean matches = passwordEncoder.matches(passwd, encodePasswd);
        User user = userService.getUserByEmail(email);
        if(user != null && user.getPasswd() != null){
            PasswordEncoder passwordEncoder =
                    PasswordEncoderFactories.createDelegatingPasswordEncoder();
            boolean matches = passwordEncoder.matches(passwd, user.getPasswd());
            if(matches){
                // 로그인정보를 세션에 저장.
                HttpSession session = req.getSession();
                session.setAttribute("logininfo", user);
                System.out.println("암호 확인.");
            }else{
                // 암호가 틀렸어요.
                System.out.println("암호가 틀림.");
            }
        }
        // 로그인 성공했다면
        resp.sendRedirect("/freeboard");
    }
}
