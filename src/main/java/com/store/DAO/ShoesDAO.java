package com.store.DAO;

import com.store.model.Customer;
import com.store.model.Shoes;
import com.store.model.ShoesType;
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

    public static List<Shoes> getShoesByTypeId(int typeId){
        List<Shoes> shoes = new ArrayList<Shoes>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM shoes";

            if (typeId!=-1){
                sql+= " where typeID=" + Integer.toString((typeId));
            }
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                Shoes s = extractShoesFromResultSet(rs);
                s.setType(ShoesTypeDAO.getShoesTypeById(s.getTypeID()));
                s.getType().setBrand(BrandDAO.getBrandById(s.getType().getBrandId()));
                shoes.add(s);
            }
            return shoes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Shoes> getShoesByBrandId(int brandId){
        List<Shoes> shoes = new ArrayList<Shoes>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM shoes";

            if (brandId!=-1) {
                sql += " where exists (select * from shoes_type where shoes.typeID = shoes_type.id";
                sql += " and shoes_type.brandID = " + Integer.toString(brandId) + ")";
            }

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                Shoes s = extractShoesFromResultSet(rs);
                s.setType(ShoesTypeDAO.getShoesTypeById(s.getTypeID()));
                s.getType().setBrand(BrandDAO.getBrandById(s.getType().getBrandId()));
                shoes.add(s);
            }
            return shoes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Shoes> getShoesByGenderId(int genderId){
        List<Shoes> shoes = new ArrayList<Shoes>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM shoes";

            if (genderId!=0){
                sql+=" where genderID = "+Integer.toString(genderId);
            }

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                Shoes s = extractShoesFromResultSet(rs);
                s.setType(ShoesTypeDAO.getShoesTypeById(s.getTypeID()));
                s.getType().setBrand(BrandDAO.getBrandById(s.getType().getBrandId()));
                shoes.add(s);
            }
            return shoes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Shoes> getShoesByPrice(int min, int max){
        List<Shoes> shoes = new ArrayList<Shoes>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM shoes";

            sql += " where price >= " + Integer.toString(min);
            sql += " and price <= " + Integer.toString(max);

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                Shoes s = extractShoesFromResultSet(rs);
                s.setType(ShoesTypeDAO.getShoesTypeById(s.getTypeID()));
                s.getType().setBrand(BrandDAO.getBrandById(s.getType().getBrandId()));
                shoes.add(s);
            }
            return shoes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Shoes getShoesById(int id) {

        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shoes where id="+Integer.toString(id));
            while(rs.next())
            {
                return extractShoesFromResultSet(rs);
            }
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
