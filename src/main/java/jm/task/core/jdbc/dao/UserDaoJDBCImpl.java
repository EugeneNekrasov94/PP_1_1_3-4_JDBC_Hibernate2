package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String table = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO users (name,lastName,age) VALUES(?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id,name,lastName,age FROM users")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE users")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
