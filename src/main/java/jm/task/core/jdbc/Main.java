package jm.task.core.jdbc;

import com.mysql.jdbc.Driver;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoJDBC = new UserDaoHibernateImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Kenny","McCormick",  (byte) 10);
        System.out.println("User с именем Kenny добавлен в базу данных");
        userDaoJDBC.saveUser("Stan","Marsh",  (byte) 10);
        System.out.println("User с именем Stan добавлен в базу данных");
        userDaoJDBC.saveUser("Kyle","Broflovski",  (byte) 9);
        System.out.println("User с именем Kyle добавлен в базу данных");
        userDaoJDBC.saveUser("Eric","Cartman",  (byte) 10);
        System.out.println("User с именем Eric добавлен в базу данных");
        List<User> list = userDaoJDBC.getAllUsers();
        list.forEach(System.out::println);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

        // реализуйте алгоритм здесь
    }

}
