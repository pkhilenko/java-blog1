package servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = { "/list", "/user" })
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            String role = session.getAttribute("role").toString();

            moveToMenu(req, resp, filterChain, role);
        } else {
            moveToMenu(req, resp, filterChain,  "unknown");
        }

    }

    private void moveToMenu(HttpServletRequest req,
                            HttpServletResponse resp,
                            FilterChain filterChain,
                            String role)
            throws ServletException, IOException {


        if (role.equals("admin") || role.equals("user")) {

            filterChain.doFilter(req, resp);

        } else {

            resp.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
