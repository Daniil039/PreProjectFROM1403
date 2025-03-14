package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection con;

    public UserDaoJDBCImpl() {
        con = Util.getCon();
    }

    public void createUsersTable()  {
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255), lastName VARCHAR(255), age INT)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
             statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Метод create работает успешно");
    }

    public void dropUsersTable()  {
        String sqlDrop = "DROP TABLE IF EXISTS users";
        try (PreparedStatement statement = con.prepareStatement(sqlDrop)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Метод drop работает успешно");
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlSaveUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement statement = con.prepareStatement(sqlSaveUser)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Метод save работает успешно");
    }

    public void removeUserById(long id) {
        String sqlRemove = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlRemove)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Метод remove работает успешно");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = con.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User(id, name, lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Метод get работает успешно");
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = con.createStatement()) {
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Метод clean работает успешно");
    }
}