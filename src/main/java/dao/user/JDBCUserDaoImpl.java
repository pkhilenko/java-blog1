package dao.user;

import model.user.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDaoImpl implements UserDao {
    private static final String URL = "jdbc:mysql://localhost:3306/blog?user=best&password=best";

    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, country FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET name = ?, email = ?, country = ? where id = ?";

    private Connection connection = null;

    public JDBCUserDaoImpl() {
    }


    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        connection = DBHelper.connection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User selectUser(Long id) {
        User user = null;
        connection = DBHelper.connection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        connection = DBHelper.connection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        connection = DBHelper.connection();
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        connection = DBHelper.connection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


//    private Connection getConnection() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(URL);
//            System.out.println("CONNECTION SUCCESSFULLY");
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

}