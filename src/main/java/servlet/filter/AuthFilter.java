package servlet.filter;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = { "/login", "/" })
public class AuthFilter implements Filter {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        User user = null;

        if (nonNull(session) && nonNull(user = (User) session.getAttribute("user"))) {
            String role = user.getRole();
            moveToMenu(req, resp, role);
        } else {
            filterChain.doFilter(req, resp);
        }

    }

    private void moveToMenu(HttpServletRequest req, HttpServletResponse resp, String role)
            throws ServletException, IOException {
        if (role.equals("admin")) {
            resp.sendRedirect("/admin/");
        } else if (role.equals("user")) {
            resp.sendRedirect("/user");
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
