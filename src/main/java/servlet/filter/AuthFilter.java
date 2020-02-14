package servlet.filter;

import model.user.User;
import service.user.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/login")
public class AuthFilter implements Filter {
    UserServiceImpl userService = UserServiceImpl.getInstance();
    User user = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        //
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            String role = session.getAttribute("role").toString();

            moveToMenu(req, resp, role);


        } else if ((user = userService.login(login, password)) != null) {

            String role =  user.getRole();

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);

            moveToMenu(req, resp, role);

        } else {

            moveToMenu(req, resp, "unknown");
        }

        //

    }


    private void moveToMenu(HttpServletRequest req,
                            HttpServletResponse resp,
                            String role)
            throws ServletException, IOException {


        if (role.equals("admin")) {

            resp.sendRedirect("admin/admin-user-list");

        } else if (role.equals("user")) {

            resp.sendRedirect("list");

        } else {

            resp.sendRedirect("login");
        }
    }

    @Override
    public void destroy() {

    }
}
