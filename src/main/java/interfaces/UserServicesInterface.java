package interfaces;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserServicesInterface {
    List<User> allUser();

    User editUser(Long id);

    void createUser(User newUser);

    void updateUser(User user) throws SQLException;

    void deleteUser(Long id) throws SQLException;
}
