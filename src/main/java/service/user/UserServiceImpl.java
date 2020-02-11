package service.user;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import model.user.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserDao dao() {
        if (userDao != null) {
            return userDao;
        }
        userDao = new UserDaoImpl();
        return userDao;
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