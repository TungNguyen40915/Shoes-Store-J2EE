package com.store.DAO;

import com.store.model.Cart;
import com.store.model.CartItem;
import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private static Connection connection;

    public static List<CartItem> getAllCartItemsByCartId(int cartId) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();
        List<CartItem> cartItems = new ArrayList<CartItem>();
        try {
            String query = "select * from CartItem Where cartID=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,cartId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cartItems.add(extractCartItemFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return cartItems;
        }
    }

    private static CartItem extractCartItemFromResultSet(ResultSet rs) throws SQLException {
        CartItem cartItem = new CartItem();
        cartItem.setId(rs.getInt("id"));
        cartItem.setCartID(rs.getInt("cartID"));
        cartItem.setAmount(rs.getInt("amount"));
        cartItem.setStockID(rs.getInt("stockID"));
        return cartItem;
    }

    public static void updateCart(String username, int stockId, int quantity) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();
        int cartId = getCartIdByUserName(username);

        if (quantity == 0) {
            deleteCartItem(cartId, stockId);
        } else {
            updateCartItem(cartId, stockId, quantity);
        }
    }

    private static void deleteCartItem(int cartId, int stockId) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();
        String SQL = "DELETE FROM CartItem where cartID=? AND stockID=?";

        try (PreparedStatement pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, String.valueOf(cartId));
            pstmt.setString(2, String.valueOf(stockId));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void updateCartItem(int cartId, int stockId, int quantity) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();
        String SQL = "UPDATE CartItem SET amount= ? where cartID=? AND stockID=?";

        try (PreparedStatement pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, String.valueOf(quantity));
            pstmt.setString(2, String.valueOf(cartId));
            pstmt.setString(3, String.valueOf(stockId));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<CartItem> addItemToCart(String username, int stockId, int quantity) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();
        int cartId = getCartIdByUserName(username);
        CartItem cartItem = getCartItemByCartIdAndStockId(cartId, stockId);
        String SQL = "";

        if (cartItem != null) {
            //update quantity
            SQL = "UPDATE CartItem SET amount= amount + ? where cartID=? AND stockID=?";
        } else {
            //create new records
            SQL = "INSERT INTO CartItem(amount, cartID, stockID)  VALUES(?,?,?)";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, String.valueOf(quantity));
            pstmt.setString(2, String.valueOf(cartId));
            pstmt.setString(3, String.valueOf(stockId));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return getAllCartItemsByCartId(cartId);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return new ArrayList<CartItem>();
    }

    private static CartItem getCartItemByCartIdAndStockId(int cartID, int stockID) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            String query = "select * from CartItem Where stockID=? AND cartID=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,stockID);
            stmt.setInt(2, cartID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return extractCartItemFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int getCartIdByUserName(String username) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            int customerId = CustomerDAO.getCustomerIdByUserName(username);
            if (customerId != -1) {
                String query = "select id from Cart Where customerID =?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1,customerId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    return rs.getInt("id");
                }
                return createCart(customerId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static int getCartIdByUserId(int customerId) {
        if (connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            if (customerId != -1) {
                String query = "select id from Cart Where customerID=?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1,customerId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    return rs.getInt("id");
                }
                return createCart(customerId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    private static int createCart(int customerId) {
        String SQL = "INSERT INTO Cart(customerID)  VALUES(?)";
        int id = -1;
        if (connection == null)
            connection = ConnectionFactory.getConnection();

        try (
                PreparedStatement pstmt = connection.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, String.valueOf(customerId));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
}
