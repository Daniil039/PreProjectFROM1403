package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDao =  new UserDaoJDBCImpl();

    public void createUsersTable()  {
        userDao.createUsersTable();
        System.out.println("Метод create из класса Service выполнен успешно");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
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