package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/popa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "daniil13261";
//    private static final String DRIVERMNAME = "com.mysql.jdbc.Driver";
    private static Connection con;

    public static Connection getCon() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OKAY");
        } catch (SQLException ex) {
            System.out.println("Connection ERROR");
        }
        return con;
    }
}