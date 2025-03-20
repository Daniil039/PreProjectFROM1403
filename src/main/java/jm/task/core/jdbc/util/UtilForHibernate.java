package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class UtilForHibernate {

    //    private static final String URL = "jdbc:mysql://localhost:3306/popa";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "daniil13261";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/popa");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "daniil13261");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();
                System.out.println("SessionFactory создана успешно.");
            } catch (Exception e) {
                System.out.println("Ошибка при создании SessionFactory: " + e);
            }
        }
        return sessionFactory;
    }
}