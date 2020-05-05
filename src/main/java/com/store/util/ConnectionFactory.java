package com.store.util;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {


    public static Connection getConnection()
    {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
    }

}
