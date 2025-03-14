package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Roman","Longer",(byte) 25);
        userService.saveUser("Ivan","Phedorov",(byte) 30);
        userService.saveUser("Viktor","Philippov",(byte) 55);
        userService.saveUser("Dmitriy", "Kopchenkov",(byte) 14);

        userService.cleanUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.saveUser("DANI", "Grishin", (byte) 23);

    }
}