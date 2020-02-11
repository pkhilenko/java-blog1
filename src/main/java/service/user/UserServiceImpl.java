package service.user;

import dao.user.UserDaoImpl;
import model.user.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDAO;

    public UserDaoImpl dao() {
        if (userDAO != null) {
            return userDAO;
        }
        userDAO = new UserDaoImpl();
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
    public void updateUser(User user) {
        dao().updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        dao().deleteUser(id);
    }
}