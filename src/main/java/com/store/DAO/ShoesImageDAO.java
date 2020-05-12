package com.store.DAO;

import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShoesImageDAO {
    public static String getImage(int id){
        Connection connection = ConnectionFactory.getConnection();
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
}
