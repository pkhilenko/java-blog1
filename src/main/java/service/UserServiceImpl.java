package service;

import dao.UserDao;
import model.User;
import util.DAOFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userServiceImpl;
    UserDao dao = new DAOFactory().getDAOFactory();

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public List<User> allUser() {
        return dao.selectAllUsers();
    }

    @Override
    public User editUser(Long id) {
        return dao.selectUser(id);
    }

    @Override
    public void createUser(User newUser) {
        dao.createUser(newUser);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        dao.deleteUser(id);
    }

    @Override
    public User login(String email, String password) {
        return dao.login(email, password);
    }
}