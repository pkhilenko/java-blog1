package interfaces;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface UserServicesInterface {
    void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    void newUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;

    void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
}
