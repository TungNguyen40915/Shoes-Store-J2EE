package com.store.DAO;

import com.store.model.Customer;
import com.store.security.Role;
import com.store.util.ConnectionFactory;
import com.store.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static String signIn(String userNameOrEmail, String password) {
        String hashedPw = Util.hashPasssword(password);
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "select username from users where (email=? or username=?) and password=?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, userNameOrEmail);
            stmt.setString(2, userNameOrEmail);
            stmt.setString(3, hashedPw);

            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
               return rs.getString("username");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Role> getUserRole(String username) {

        Connection connection = ConnectionFactory.getConnection();
        List<Role> roles = new ArrayList();
        try {
            String sql = "select roleID from user_roles";
            sql += " where exists (select * from users where users.username=? and users.id = user_roles.userID)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                int roleID = rs.getInt("roleID");
                switch(roleID){
                    case 1:
                        roles.add(Role.USER);
                        break;
                    case 2:
                        roles.add(Role.PM);
                        break;
                    case 3:
                        roles.add(Role.ADMIN);
                        break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roles;
    }

}
