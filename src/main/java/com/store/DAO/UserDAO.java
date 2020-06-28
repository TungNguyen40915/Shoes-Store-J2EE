package com.store.DAO;

import com.store.model.User;
import com.store.util.ConnectionFactory;

import java.sql.*;

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

    public static int getUserIdByUsername(String username){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select id from User Where username = \'" + username + "\'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static int insertUser(String username, String password, int roleId)  {
        if(connection == null)
            connection = ConnectionFactory.getConnection();

        if(checkIfUsernameExist(username))
            return 0; //username already registered

        String insertUser = "INSERT INTO User (username, passwordHash, roleID) VALUES (?,?,?)";
        User user = new User();

        try (PreparedStatement pstmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, String.valueOf(roleId));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return 1; //success
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return 2; //error
    }


}
