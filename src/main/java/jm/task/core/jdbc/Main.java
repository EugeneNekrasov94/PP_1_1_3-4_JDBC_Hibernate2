package jm.task.core.jdbc;

import com.mysql.jdbc.Driver;
import java.sql.*;

public class Main {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/users";
    public static final String USER = "user";
    public static final String PASSWORD = "EMBJU9hcm";
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Success");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // реализуйте алгоритм здесь
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
        return connection;
    }
}
