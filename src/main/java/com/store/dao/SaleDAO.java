package com.store.dao;

import com.store.model.Sale;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleDAO {

    private static Connection connection;

    public static Sale getSaleByShoesId(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM Sale s, SaleItem i WHERE s.id = i.saleID AND s.status = \"ACTIVE\" and s.startDate <= NOW() and s.expiredDate >= NOW() and i.deleteFlag = 0 and i.shoesID = " + id + "  LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return extractSaleFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static Sale extractSaleFromResultSet(ResultSet rs) throws SQLException {
        Sale sale = new Sale();
        sale.setId(rs.getInt("id"));
        sale.setAmount(rs.getInt("amount"));
        sale.setSaleType(rs.getString("saleType"));
        sale.setExpiredDate(rs.getDate("expiredDate"));
        sale.setStartDate(rs.getDate("startDate"));
        sale.setStatus(rs.getString("status"));
        return sale;
    }


}
