package com.store.DAO;

import com.store.model.Customer;
import com.store.model.ShoesDetail;
import com.store.model.User;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoesDetailDAO {

    public static ShoesDetail getShoesDetailById(int id) {

        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shoes_detail where id="+Integer.toString(id));
            while(rs.next())
            {
                return extractShoesDetailFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static ShoesDetail extractShoesDetailFromResultSet(ResultSet rs) throws SQLException {
        ShoesDetail detail = new ShoesDetail();
        detail.setId( rs.getInt("id") );
        detail.setQuantity( rs.getInt("quantity") );
        detail.setShoesId(rs.getInt("shoesID"));
        detail.setSize(rs.getInt("size"));
        return detail;
    }


}
