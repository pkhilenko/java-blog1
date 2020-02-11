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

@WebServlet(urlPatterns = { "/new", "/create" })
public class CreateUser extends HttpServlet {
    RequestDispatcher dispatcher = null;
    UserServiceImpl userServices = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("rootPath", request.getContextPath());
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);
        userServices.createUser(newUser);
        response.sendRedirect("list");
    }
}
