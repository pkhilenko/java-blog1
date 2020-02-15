package servlet.auth;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("main.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.login(login, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            if (user.getRole().equals("admin")) {
                resp.sendRedirect("/admin/");
            } else {
                resp.sendRedirect("/user");
            }
        } else {
            resp.sendRedirect("/login");
        }


    }
}
