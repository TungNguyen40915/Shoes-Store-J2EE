package com.store.DAO;

import com.store.model.Shoes;
import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminShoesDAO {
    private static Connection connection;

    public static List<Shoes> getAllShoes(int page, int pageSize, int styleId, int brandId, String search, int price, int minPrice, int maxPrice) {
        List<Shoes> shoes = new ArrayList<Shoes>();

        if (connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM Shoes";
            query = query.concat(handleQueryStatement(page, pageSize, styleId, brandId, search, price, minPrice, maxPrice));
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Shoes s = extractShoesFromResultSet(rs);
                shoes.add(s);
            }
            return shoes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int getTotalRecords(int page, int pageSize, int styleId, int brandId, String search, int price, int minPrice, int maxPrice) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();

        try {
            Statement stmt = connection.createStatement();
            String query = "Select count(*) as total from Shoes ";
            query = query.concat(handleQueryStatement(0, 0, styleId, brandId, search, price, minPrice, maxPrice));
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static Shoes getShoesById(int id) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM Shoes WHERE id = " + id + "  LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
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
        shoes.setDescription(rs.getString("description"));
        shoes.setRating(rs.getFloat("rating"));
        shoes.setStyleID(rs.getInt("styleID"));
        shoes.setBrandID(rs.getInt("brandID"));
        shoes.setGenderID(rs.getInt("genderID"));
        shoes.setPrice(rs.getInt("price"));
        shoes.setIsNew(rs.getInt("isNew"));
        shoes.setIsOnSale(rs.getInt("isOnSale"));
        shoes.setCreateDate(rs.getDate("createDate"));
        shoes.setDeleteFlag(rs.getInt("deleteFlag"));
        shoes.setRatingCount(rs.getInt("ratingCount"));
        return shoes;
    }

    private static String handleQueryStatement(int page, int pageSize, int styleId, int brandId, String search, int price, int minPrice, int maxPrice) {
        String query = "";
        List<String> statement = new ArrayList<String>();

        if (styleId != 0 || search != null || brandId != 0 || price != 0 || minPrice != 0 || maxPrice != 0) {
            if (styleId != 0) {
                statement.add("styleID =" + styleId);
            }
            if (brandId != 0) {
                statement.add("brandID =" + brandId);
            }
            if (search != null) {
                statement.add("name like \'%" + search + "%\'  OR Code like \'%" + search + "%\'");
            }
            if (price != 0) {
                statement.add(" price = " + price);
            }

            statement.add(" price >= " + minPrice);

            if (maxPrice != 0) {
                statement.add(" price <= " + maxPrice);
            }

            query = query.concat("  WHERE ");
            query = query.concat(statement.get(0));
            for (int i = 1; i < statement.size(); i++) {
                query = query.concat("  AND  ");
                query = query.concat(statement.get(i));
            }
        }

        query = query.concat(" order by createDate DESC");

        if(pageSize == 0)
            pageSize = 20;

        query = query.concat(" LIMIT " + pageSize);

        if (page != 0) {
            query = query.concat("  OFFSET  " + (page * pageSize));
        }

        return query;
    }
}
