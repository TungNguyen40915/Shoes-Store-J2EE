package com.store.DAO;

import com.store.model.CartItem;
import com.store.model.Size;
import com.store.model.Stock;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private static Connection connection;

    public static int getTotalQuantity(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
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


    public static List<String> getAllSizeByShoesID(int id){
        List<String> list = new ArrayList<String>();
        String temp = "";
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select sizeID from Stock Where instock > 0 AND shoesID =" + id;
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                temp = SizeDAO.getSizeName(rs.getInt("sizeID"));
                if(temp != "")
                    list.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static Stock getStockById(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select * from Stock Where id =" + id;
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return extractStockFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static Stock extractStockFromResultSet(ResultSet rs) throws SQLException {
        Stock stock = new Stock();
        stock.setId(rs.getInt("id"));
        stock.setInstock(rs.getInt("instock"));
        stock.setShoesID(rs.getInt("shoesID"));
        stock.setSizeID(rs.getInt("sizeID"));

        return stock;
    }

}
