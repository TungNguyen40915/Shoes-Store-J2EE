package com.store.DAO;

import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    private static Connection connection;

    public static String signin(String username, String password){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select username from User Where username = \'" + username + "\'   AND   passwordHash =  \'" + password + "\'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return rs.getString("username");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static boolean checkIfUsernameExist(String username){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select username from User Where username = \'" + username + "\'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


}
