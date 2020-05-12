package com.store.DAO;

import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoesImageDAO {
    private static Connection connection;

    public static String getImage(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select imagePath from ShoesImage WHERE shoesID = " + id + "  order by id LIMIT 1 ";
            ResultSet rs = stmt.executeQuery(query);
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
            Statement stmt = connection.createStatement();
            String query = "select imagePath from ShoesImage WHERE shoesID = " + id + "  order by id ";
            ResultSet rs = stmt.executeQuery(query);
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
