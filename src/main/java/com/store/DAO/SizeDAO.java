package com.store.DAO;

import com.store.model.Gender;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SizeDAO {
    private static Connection connection;

    public static int getSizeID(String sizeName){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT id FROM Size Where name = \"" + sizeName + "\" AND deleteFlag = 0 LIMIT 1;";
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

    public static String getSizeName(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT name FROM Size Where id = \"" + id + "\" LIMIT 1;";
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
    public static List<String> getAllSizes(){
        List<String> list = new ArrayList<String>();
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT name FROM Size";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                list.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
