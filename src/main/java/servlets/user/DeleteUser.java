package servlets.user;

import service.user.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteUser extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserServiceImpl userServices = new UserServiceImpl();
        long id = Long.parseLong(request.getParameter("id"));
        userServices.deleteUser(id);
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}