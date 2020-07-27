package com.store.dao;

import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoesImageDAO {
    private static Connection connection;

    public static String getImage(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "select imagePath from ShoesImage WHERE shoesID=? order by id LIMIT 1 ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                return rs.getString("imagePath");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static List<String> getAllImagesById(int id){
        List<String> list = new ArrayList<String>();
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "select imagePath from ShoesImage WHERE shoesID=?  order by id ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                list.add(rs.getString("imagePath"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
