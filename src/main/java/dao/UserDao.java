package dao;

import model.User;

import java.util.List;

public interface UserDao {
    List<User> selectAllUsers();

    User selectUser(Long id);

    void createUser(User user);

    void updateUser(User user, String currentEmail);

    void deleteUser(Long id);

    boolean isExistsUser(String email);

    User login(String email, String password);
}
