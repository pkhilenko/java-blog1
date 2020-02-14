package servlet.admin;

import model.user.User;
import service.user.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/admin-user-list", "/admin/user-list", "/admin"})
public class AdminListUser extends HttpServlet {
    RequestDispatcher dispatcher = null;
    UserServiceImpl userServices = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<User> listUser = userServices.allUser();
        request.setAttribute("listUser", listUser);
        dispatcher = request.getRequestDispatcher("admin-user-list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
