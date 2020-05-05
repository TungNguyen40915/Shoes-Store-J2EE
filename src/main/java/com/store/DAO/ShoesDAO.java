package com.store.DAO;

import com.store.model.Shoes;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoesDAO {
    public static List<Shoes> getAllShoes() {
        List<Shoes> shoes = new ArrayList<Shoes>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shoes");
            while(rs.next())
            {
                Shoes s = extractShoesFromResultSet(rs);
                shoes.add(s);
            }
            return shoes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static Shoes extractShoesFromResultSet(ResultSet rs) throws SQLException {
        Shoes shoes = new Shoes();
        shoes.setId(rs.getInt("id"));
        shoes.setCode(rs.getString("code"));
        shoes.setName(rs.getString("name"));
        shoes.setPrice(rs.getInt("price"));
        shoes.setRemark(rs.getString("remark"));
        shoes.setImg1(rs.getString("img1"));
        shoes.setImg2(rs.getString("img2"));
        shoes.setImg3(rs.getString("img3"));
        shoes.setImg4(rs.getString("img4"));
        shoes.setQuantity(rs.getInt("quantity"));
        shoes.setTypeID(rs.getInt("typeID"));
        shoes.setGenderID(rs.getInt("genderID"));
        shoes.setDeleteFlag(rs.getBoolean("deleteFlag"));
        return shoes;
    }
}
