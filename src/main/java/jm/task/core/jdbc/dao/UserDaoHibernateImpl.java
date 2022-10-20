package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private Session session;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)").addEntity(User.class).executeUpdate();

        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        session = getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session = getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session = getSessionFactory().openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("SELECT i from User i", User.class).getResultList();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
        session.getTransaction().commit();
    }
}
