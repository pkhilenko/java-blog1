package servlets;

import DAO.UserDAO;
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
    private static final long serialVersionUID = 1L;


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
                    userServices.newUser(request, response);
                    break;
                case "/create":
                    userServices.createUser(request, response);
                    break;
                case "/delete":
                    userServices.deleteUser(request, response);
                    break;
                case "/edit":
                    userServices.editUser(request, response);
                    break;
                case "/update":
                    userServices.updateUser(request, response);
                    break;
                default:
                    userServices.allUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

}