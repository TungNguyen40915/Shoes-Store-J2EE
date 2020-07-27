package com.store.dao;

import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoesStyleDAO {
    private static Connection connection;

    public static int getStyleID(String styleName){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "SELECT id FROM ShoesType Where name = ? AND deleteFlag = 0 LIMIT 1;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,styleName);
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

    public static String getStyleName(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "SELECT name FROM ShoesType Where id = ? AND deleteFlag = 0 LIMIT 1;";
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

    public static List<String> getAllTypes(){
        List<String> list = new ArrayList<String>();
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT name FROM ShoesType";
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
