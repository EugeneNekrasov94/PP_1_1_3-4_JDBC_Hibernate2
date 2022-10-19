package jm.task.core.jdbc;

import com.mysql.jdbc.Driver;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.saveUser("Eugene","Nekrasov",  (byte) 28);
        userDaoJDBC.saveUser("Eugene","Nekrasov",  (byte) 28);
        userDaoJDBC.saveUser("Eugene","Nekrasov",  (byte) 28);
        userDaoJDBC.saveUser("Eugene","Nekrasov",  (byte) 28);
        List<User> list = userDaoJDBC.getAllUsers();
        list.forEach(System.out::println);
        //userDaoJDBC.removeUserById(1);
        userDaoJDBC.cleanUsersTable();

        // реализуйте алгоритм здесь
    }

}
