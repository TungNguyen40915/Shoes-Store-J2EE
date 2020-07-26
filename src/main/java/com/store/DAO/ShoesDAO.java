package com.store.DAO;

import com.store.model.Shoes;
import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoesDAO {

    private static Connection connection;

    public static List<Shoes> getAllShoes(String gender, String isNew, String sale, String size, String page, String style, String brand, String search, String pageSize, String priceRange) {
        List<Shoes> shoes = new ArrayList<Shoes>();

        if (connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM Shoes";
            query = query.concat(handleQueryStatement(gender, isNew, sale, size, page, style, brand, search, pageSize, priceRange));
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

    public static int getTotalRecords(String gender, String isNew, String sale, String size, String page, String style, String brand, String search, String pageSize, String priceRange) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();

        try {
            Statement stmt = connection.createStatement();
            String query = "Select count(*) as total from Shoes ";
            query = query.concat(handleQueryStatement(gender, isNew, sale, size, page, style, brand, search, pageSize, priceRange));
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static Shoes ratingShoes(int id, float rating) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();

        Shoes shoes = getShoesById(StockDAO.getStockById(id).getShoesID());

        int updatedRatingCount = shoes.getRatingCount() + 1;
        float updatedRating = (shoes.getRating() + rating) / updatedRatingCount;

        String SQL = "UPDATE Shoes SET rating = ?, ratingCount = ? where id= ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, String.valueOf(updatedRating));
            pstmt.setString(2, String.valueOf(updatedRatingCount));
            pstmt.setString(3, String.valueOf(id));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return ShoesDAO.getShoesById(shoes.getId());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
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

    private static String handleQueryStatement(String gender, String isNew, String sale, String size, String page, String style, String brand, String search, String pageSize, String priceRange) {
        String query = "";
        List<String> statement = new ArrayList<String>();

        if (gender != null || isNew != null || sale != null || size != null || style != null || search != null || brand != null || priceRange != null) {
            if (gender != null) {
                statement.add("genderID = " + GenderDAO.getGenderID(gender));
            }
            if (isNew != null) {
                statement.add(" isNew = 0");
            }
            if (sale != null) {
                statement.add("isOnSale = 0");
            }
            if (style != null) {
                statement.add("styleID =" + ShoesStyleDAO.getStyleID(style));
            }
            if (brand != null) {
                statement.add("brandID =" + ShoesBrandDAO.getBrandID(brand));
            }
            if (search != null) {
                statement.add("name like \'%" + search + "%\'  OR Code like \'%" + search + "%\'");
            }
            if (priceRange != null) {
                switch (priceRange) {
                    case "1":
                        statement.add(" price <= 1000000 ");
                        break;
                    case "2":
                        statement.add("  price >= 1000000 AND price <= 3000000 ");
                        break;
                    case "3":
                        statement.add("  price >= 3000000 AND price <= 5000000");
                        break;
                    case "4":
                        statement.add("  price >= 5000000 AND price <= 10000000");
                        break;
                    case "5":
                        statement.add(" price >= 10000000 ");
                        break;
                    default:
                        break;
                }
            }
            query = query.concat("  WHERE ");
            query = query.concat(statement.get(0));
            for (int i = 1; i < statement.size(); i++) {
                query = query.concat("  AND  ");
                query = query.concat(statement.get(i));
            }
        }
        query = query.concat(" order by createDate DESC");

        if(pageSize == null)
            pageSize = "20";

        query = query.concat(" LIMIT " + pageSize);

        if (page != null) {
            query = query.concat("  OFFSET  " + Integer.parseInt(page) * Integer.parseInt(pageSize));
        }
        return query;
    }
}
