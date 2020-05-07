package com.store.DAO;

import com.store.model.Brand;
import com.store.model.ShoesType;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {

    public static List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<Brand>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM brand");
            while(rs.next())
            {
                Brand brandsTemp = extractBrandFromResultSet(rs);
                brands.add(brandsTemp);
            }
            return brands;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Brand> getAllBrandsWithType() {
        List<Brand> brands = new ArrayList<Brand>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM brand");
            while(rs.next())
            {
                Brand brandTemp = extractBrandFromResultSet(rs);
                brandTemp.setTypes(ShoesTypeDAO.getShoesTypesByBrandId(brandTemp.getId()));
                brands.add(brandTemp);
            }
            return brands;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Brand getBrandById(int id){
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM brand where id = " + Integer.toString(id));
            while(rs.next())
            {
                return extractBrandFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static Brand extractBrandFromResultSet(ResultSet rs) throws SQLException {
        Brand brand = new Brand();
        brand.setId( rs.getInt("id") );
        brand.setName( rs.getString("name") );
        return brand;
    }


}
