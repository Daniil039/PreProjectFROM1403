package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.UtilForHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = UtilForHibernate.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), " +
                    "lastName VARCHAR(255), " +
                    "age INT)").executeUpdate();
            tx.commit();
            System.out.println("table - create");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("error crate");
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            System.out.println("table - drop");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("error drop");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx.commit();
            System.out.println("table - save");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("error save");
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(session.get(User.class, id));
            tx.commit();
            System.out.println("User deleted");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("error remove");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
            tx.commit();
            System.out.println("User getALl");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("error of getALL");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            tx.commit();
            System.out.println("User table clean!");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("error of clean");
        }
    }
}