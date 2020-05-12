package com.store.DAO;

import com.store.model.Gender;
import com.store.model.Shoes;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenderDAO {

    private static Connection connection;

    public static int getGenderID(String genderName){

        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT id FROM Gender Where name = \"" + genderName + "\" LIMIT 1;";
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

    public static String getGenderName(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT name FROM Gender Where id = \"" + id + "\" LIMIT 1;";
            ResultSet rs = stmt.executeQuery(query);
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
