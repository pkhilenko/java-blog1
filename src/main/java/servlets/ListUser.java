package servlets;

import model.User;
import service.userservice.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/list", "/" })
public class ListUser extends HttpServlet {
    RequestDispatcher dispatcher = null;
    UserServiceImpl userServices = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = userServices.allUser();
        request.setAttribute("listUser", listUser);
        request.setAttribute("rootPath", request.getContextPath());
        dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }
}
