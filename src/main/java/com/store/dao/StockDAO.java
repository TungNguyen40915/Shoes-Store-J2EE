package com.store.dao;

import com.store.dto.ShoesSizeDTO;
import com.store.model.Stock;
import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private static Connection connection;

    public static int getTotalQuantity(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "select SUM(instock) as total from Stock Where shoesID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }


    public static List<ShoesSizeDTO> getAllSizeByShoesID(int id){
        List<ShoesSizeDTO> list = new ArrayList<ShoesSizeDTO>();
        String temp = "";
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "select id,sizeID from Stock Where instock > 0 AND shoesID =?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                temp = SizeDAO.getSizeName(rs.getInt("sizeID"));
                int stockId = rs.getInt("id");
                if(temp != "")
                    list.add(new ShoesSizeDTO(stockId,temp));
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
            String query = "select * from Stock Where id =?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                return extractStockFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Stock getStockByShoesIDAndSizeID(int shoesId, int sizeId){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "select * from Stock Where shoesID = ? AND sizeID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,shoesId);
            stmt.setInt(2,sizeId);
            ResultSet rs = stmt.executeQuery();
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
