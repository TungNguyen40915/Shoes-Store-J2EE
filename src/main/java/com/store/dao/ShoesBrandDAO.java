package com.store.dao;

import com.store.model.ShoesBrand;
import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoesBrandDAO {
    private static Connection connection;

    public static int getBrandID(String brandName){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "SELECT id FROM ShoesBrand Where name=? AND deleteFlag = 0 LIMIT 1;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,brandName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static String getBrandName(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "SELECT name FROM ShoesBrand Where id=? AND deleteFlag = 0 LIMIT 1;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                return rs.getString("name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static List<String> getAllBrands(){
        List<String> list = new ArrayList<String>();
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT name FROM ShoesBrand";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                list.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static List<ShoesBrand> getAll(){
        List<ShoesBrand> list = new ArrayList<ShoesBrand>();
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM ShoesBrand";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                list.add(extractShoesBrandFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private static ShoesBrand extractShoesBrandFromResultSet(ResultSet rs) throws SQLException {
        ShoesBrand brand = new ShoesBrand();
        brand.setId(rs.getInt("id"));
        brand.setCountry(rs.getString("country"));
        brand.setName(rs.getString("name"));
        return brand;
    }
}
