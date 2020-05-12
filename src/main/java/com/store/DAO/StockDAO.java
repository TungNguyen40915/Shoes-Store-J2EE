package com.store.DAO;

import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StockDAO {

    public static int getTotalQuantity(int id){
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select SUM(instock) as total from Stock Where shoesID = " + id;
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
