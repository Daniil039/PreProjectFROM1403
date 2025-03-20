package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable()  {
        try {
            userDao.createUsersTable();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Метод create из класса Service выполнен успешно");
    }

    public void dropUsersTable() {
        try {
            userDao.dropUsersTable();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Метод drop из класса Service выполнен успешно");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.println("Метод remove из класса Service выполнен успешно");
    }

    public List<User> getAllUsers() {
        List<User> users =  userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("Метод getAll из класса Service выполнен успешно");
        return users;
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("Метод clean из класса Service выполнен успешно");
    }
}