package servlet.admin;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class UserDeleteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserService userServices = UserServiceImpl.getInstance();
        long id = Long.parseLong(request.getParameter("id"));
        userServices.deleteUser(id);
        try {
            response.sendRedirect("/admin/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}