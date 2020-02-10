package servlets;

import model.User;
import services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    RequestDispatcher dispatcher = null;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        UserServices userServices = new UserServices();

        try {
            switch (action) {
                case "/new":
                    dispatcher = request.getRequestDispatcher("user-form.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "/create":
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String country = request.getParameter("country");
                    User newUser = new User(name, email, country);

                    userServices.createUser(newUser);
                    response.sendRedirect("list");
                    break;
                case "/delete":
                    long id = Long.parseLong(request.getParameter("id"));
                    userServices.deleteUser(id);
                    response.sendRedirect("list");
                    break;
                case "/edit":
                    id = Long.parseLong(request.getParameter("id"));
                    User existingUser = userServices.editUser(id);
                    dispatcher = request.getRequestDispatcher("user-form.jsp");
                    request.setAttribute("user", existingUser);
                    dispatcher.forward(request, response);
                    break;
                case "/update":
                    id = Long.parseLong(request.getParameter("id"));
                    name = request.getParameter("name");
                    email = request.getParameter("email");
                    country = request.getParameter("country");
                    User user = new User(id, name, email, country);
                    userServices.updateUser(user);
                    response.sendRedirect("list");
                    break;
                default:
                    List<User> listUser = userServices.allUser();
                    request.setAttribute("listUser", listUser);
                    dispatcher = request.getRequestDispatcher("user-list.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

}