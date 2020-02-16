package servlet.admin;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/admin/edit")
public class UserEditServlet extends HttpServlet {
    private UserService userServices = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = null;
        long id = Long.parseLong(request.getParameter("id"));
        User existingUser = userServices.editUser(id);
        dispatcher = request.getRequestDispatcher("admin-edit-user-form.jsp");
        request.setAttribute("existingUser", existingUser);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        User user = new User(id, name, email, country, role, password);
        userServices.updateUser(user);
        try {
            response.sendRedirect("/admin/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
