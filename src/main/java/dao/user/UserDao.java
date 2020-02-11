package dao.user;

import model.user.User;

import java.util.List;

public interface UserDao {
    List<User> selectAllUsers();

    User selectUser(Long id);

    void createUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(Long id);
}
