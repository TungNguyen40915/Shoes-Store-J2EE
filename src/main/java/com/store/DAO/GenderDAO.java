package com.store.DAO;

import com.store.model.Gender;
import com.store.model.Shoes;
import com.store.util.ConnectionFactory;

import java.sql.*;

public class GenderDAO {

    private static Connection connection;

    public static int getGenderID(String genderName){

        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "SELECT id FROM Gender Where name=? LIMIT 1;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,genderName);
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

    public static String getGenderName(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();

        try {
            String query = "SELECT name FROM Gender Where id=? LIMIT 1;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                return rs.getString("name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }


}
