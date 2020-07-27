package com.store.dao;

import com.store.model.User;
import com.store.util.ConnectionFactory;

import java.sql.*;

public class UserDAO {
    private static Connection connection;

    public static String signin(String username, String password){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "select username from User Where username = ? AND passwordHash = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,username);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
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
            String query = "select username from User Where username = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
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
            String query = "select id from User Where username = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
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

    public static User getUserByUsername(String username){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "select * from User Where username = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRoleID(rs.getInt("roleID"));
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
