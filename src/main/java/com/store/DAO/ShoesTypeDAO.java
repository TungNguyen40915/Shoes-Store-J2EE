package com.store.DAO;

import com.store.model.Brand;
import com.store.model.Gender;
import com.store.model.Shoes;
import com.store.model.ShoesType;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoesTypeDAO {

    public static List<ShoesType> getAllShoesTypes() {
        List<ShoesType> types = new ArrayList<ShoesType>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shoes_type");
            while(rs.next())
            {
                ShoesType typeTemp = extractShoesTypeFromResultSet(rs);
                typeTemp.setBrand(BrandDAO.getBrandById(typeTemp.getBrandId()));
                types.add(typeTemp);
            }
            return types;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ShoesType getShoesTypeById(int id) {

        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shoes_type where id="+Integer.toString(id));
            while(rs.next())
            {
                return extractShoesTypeFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<ShoesType> getShoesTypesByBrandId(int brandId) {
        List<ShoesType> types = new ArrayList<ShoesType>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shoes_type where brandID=" +Integer.toString(brandId));
            while(rs.next())
            {
                ShoesType typesTemp = extractShoesTypeFromResultSet(rs);
                types.add(typesTemp);
            }
            return types;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static ShoesType extractShoesTypeFromResultSet(ResultSet rs) throws SQLException {
        ShoesType type = new ShoesType();
        type.setId( rs.getInt("id") );
        type.setName( rs.getString("name") );
        type.setBrandId(rs.getInt("brandID"));
        return type;
    }


}
