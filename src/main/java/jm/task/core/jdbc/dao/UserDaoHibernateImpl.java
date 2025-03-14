package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.UtilForHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = UtilForHibernate.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        // Открываем новую сессию Hibernate для взаимодействия с базой данных.
        Session session = sessionFactory.openSession();
        //Инициализирует переменную transaction как null. null чтобы можно было отктаить с помощью rollback
        Transaction transaction = null;
        try {
            // Начало новой транзакции.
            transaction = session.beginTransaction();
            // Выполнение скл запроса
            int CUT = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), " +
                    "lastName VARCHAR(255), " +
                    "age INT, " +
                    "PRIMARY KEY (id))").executeUpdate();
            // фиксация транзакции
            transaction.commit();
            System.out.println("table - create");
        } catch (Exception e) {
            // В случае исключения откатывает транзакцию, если она была начата, и выводит текст.
            if (transaction != null) transaction.rollback();
            System.out.println("error of create");
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            int dropTableIfExistsUsers = session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            System.out.println("table - drop");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("error of drop");
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("table - save");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("error of save");
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("User deleted");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("error of save");
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> users = null;
        try {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
            transaction.commit();
            System.out.println("User getALl");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("error of getALL");
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            System.out.println("User table clean!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("error of clean");
        } finally {
            session.close();
        }
    }
}