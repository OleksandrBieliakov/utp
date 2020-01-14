package ass10.repositories;

import ass10.dtos.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/academy";
    private static final String USER = "oleksandr";
    private static final String PASSWORD = "topsecret";

    private Connection _connection;

    public UserRepository() {
        try {
            _connection = DriverManager.getConnection(URL, USER, PASSWORD);
            _connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserDTO> findByName(String username) {
        List<UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "SELECT user_login, user_password " +
                            "FROM users " +
                            "WHERE user_login LIKE ?");
            statement.setString(1, "%" + username + "%");
            ResultSet result = statement.executeQuery();
            int id;
            String userLogin;
            String userPassword;
            while (result.next()) {
                id = result.getInt("user_id");
                userLogin = result.getString("user_login");
                userPassword = result.getString("user_password");
                users.add(new UserDTO(id, userLogin, userPassword));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Connection getConnection() {
        return _connection;
    }

    @Override
    public void add(UserDTO dto) {
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "insert into users(user_login, user_password)" +
                            "values (?, ?)");
            statement.setString(1, dto.getLogin());
            statement.setString(2, dto.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDTO dto) {
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "update users " +
                            "set user_login=?, user_password=?" +
                            "where user_id=?");
            statement.setString(1, dto.getLogin());
            statement.setString(2, dto.getPassword());
            statement.setInt(3, dto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(UserDTO dto) {
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "INSERT INTO users (user_id, user_login, user_password) " +
                            "VALUES (?, ?, ?) " +
                            "ON CONFLICT (user_id) DO UPDATE " +
                            "SET user_login=?, user_password=?");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getLogin());
            statement.setString(3, dto.getPassword());
            statement.setString(4, dto.getLogin());
            statement.setString(5, dto.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserDTO dto) {
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "delete from users " +
                            "where user_id=?");
            statement.setInt(1, dto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO findById(int id) {
        UserDTO user = null;
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "SELECT user_login, user_password " +
                            "FROM USERS WHERE user_id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            String userLogin;
            String userPassword;
            if (result.next()) {
                userLogin = result.getString("user_login");
                userPassword = result.getString("user_password");
                user = new UserDTO(id, userLogin, userPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void beginTransaction() {
        try {
            _connection.setSavepoint();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {
        try {
            _connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            _connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            PreparedStatement statement = _connection.prepareStatement("SELECT count(1) from users");
            ResultSet result = statement.executeQuery();
            count = result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public boolean exists(UserDTO dto) {
        boolean exists = false;
        try {
            PreparedStatement statement = _connection.prepareStatement("SELECT count(1) from users where user_id=?");
            statement.setInt(1, dto.getId());
            ResultSet result = statement.executeQuery();
            if (result.getInt(1) > 0)
                exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
