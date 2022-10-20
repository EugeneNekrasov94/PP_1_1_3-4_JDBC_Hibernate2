package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String USER = "root";
    private static final String PASSWORD = "QWE11zxc";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties props = new Properties();
                props.put(Environment.DRIVER, DRIVER);
                props.put(Environment.URL, URL);
                props.put(Environment.USER, USER);
                props.put(Environment.PASS, PASSWORD);

                props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                props.put(Environment.SHOW_SQL, "true");
                props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                props.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(props);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    // реализуйте настройку соеденения с БД
}
