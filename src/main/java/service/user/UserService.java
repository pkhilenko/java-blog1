package service.user;

import model.user.User;

import java.util.List;

public interface UserService {
    List<User> allUser();

    User editUser(Long id);

    void createUser(User newUser);

    void updateUser(User user, String currentEmail);

    void deleteUser(Long id);

    User login(String email, String password);
}
