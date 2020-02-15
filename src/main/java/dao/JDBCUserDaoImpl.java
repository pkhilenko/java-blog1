package dao;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDaoImpl implements UserDao {
    private static final String INSERT_USERS_SQL =
            "INSERT INTO users (name, email, country, role, password) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID =
            "SELECT id, name, email, country, role, password FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_EMAIL =
            "SELECT email FROM users WHERE email = ?";
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT id, name, email, country, role, password FROM users WHERE email = ? AND password = ?";
    private static final String SELECT_ALL_USERS =
            "SELECT * FROM users";
    private static final String DELETE_USERS_SQL =
            "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USERS_SQL =
            "UPDATE users SET name = ?, email = ?, country = ?, role = ?, password = ? WHERE id = ?";

    private Connection connection = null;
    DBHelper dbHelper;

    public JDBCUserDaoImpl() {
        this.dbHelper = DBHelper.getInstance();
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        connection = dbHelper.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String role = rs.getString("role");
                users.add(new User(id, name, email, country, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User selectUser(Long id) {
        User user = null;
        connection = dbHelper.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String role = rs.getString("role");
                String password = rs.getString("password");
                user = new User(id, name, email, country, role, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        connection = dbHelper.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);) {
            String name = user.getName();
            String email = user.getEmail();
            String country = user.getCountry();
            String role = user.getRole();
            String password = user.getPassword();
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, country);
            preparedStatement.setString(4, role);
            preparedStatement.setString(5, password);

            if (isExistsUser(email)) {
                preparedStatement.close();
            } else {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        connection = dbHelper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);
            String name = user.getName();
            String email = user.getEmail();
            String oldEmail = selectUser(user.getId()).getEmail();
            String country = user.getCountry();
            String role = user.getRole();
            String password = user.getPassword();

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, country);
            preparedStatement.setString(4, role);
            preparedStatement.setString(5, password);
            preparedStatement.setLong(6, user.getId());

            if (isExistsUser(email) && !email.equals(oldEmail)) {
                preparedStatement.close();
            } else {
                preparedStatement.executeUpdate();
            }
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
        connection = dbHelper.getConnection();
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

    @Override
    public boolean isExistsUser(String email) {
        connection = dbHelper.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User login(String email, String password) {
        connection = dbHelper.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                String role = rs.getString("role");
                return new User(id, name, email, country, role, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}