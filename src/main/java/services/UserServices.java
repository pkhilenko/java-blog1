package services;

import DAO.UserDAO;
import interfaces.UserServicesInterface;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServices implements UserServicesInterface {

    private UserDAO userDAO;

    public UserDAO dao() {
        if (userDAO != null) {
            return userDAO;
        }
        userDAO = new UserDAO();
        return userDAO;
    }

    @Override
    public List<User> allUser() {
        return dao().selectAllUsers();
    }

    @Override
    public User editUser(Long id) {
        return dao().selectUser(id);
    }

    @Override
    public void createUser(User newUser) {
        dao().createUser(newUser);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        dao().updateUser(user);
    }

    @Override
    public void deleteUser(Long id)throws SQLException {
        dao().deleteUser(id);
    }
}