package com.store.DAO;

import com.store.model.*;
import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public static Order getOrderById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shoes_order where id="+Integer.toString(id));
            while(rs.next())
            {
                Order order = extractOrderFromResultSet(rs);

                order.setStatus(StatusDAO.getStatusById(order.getStatusID()));

                Customer customer = CustomerDAO.getCustomerById(order.getCustomerID());
                customer.setUser(UserDAO.getUserById(customer.getUserId()));

                order.setCustomer(customer);

                return order;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean updateStatus(int orderId,int statusID) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "update shoes_order" +
                    " set statusID = ?" +
                    " where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1,statusID);
            stmt.setInt(2,orderId);

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static List<OrderDetail> getOrderDetailsByOrderId(int id){
        List<OrderDetail> details = new ArrayList<OrderDetail>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM order_detail where orderID="+Integer.toString(id));
            while(rs.next())
            {
                OrderDetail detail = extractOrderDetailFromResultSet(rs);
                ShoesDetail shoesDetail = ShoesDetailDAO.getShoesDetailById(detail.getShoesDetailId());
                Shoes shoes = ShoesDAO.getShoesById(shoesDetail.getShoesId());
                shoes.setType(ShoesTypeDAO.getShoesTypeById(shoes.getTypeID()));
                shoes.setGender(GenderDAO.getGenderById(shoes.getGenderID()));
                shoesDetail.setShoes(shoes);
                detail.setShoesDetail(shoesDetail);
                detail.setOrder(getOrderById(detail.getOrderId()));
                details.add(detail);
            }
            return details;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId( rs.getInt("id") );
        order.setOrderDate( rs.getDate("orderDate") );
        order.setDeliveryDate(rs.getDate("deliveryDate"));
        order.setStatusID(rs.getInt("statusID"));
        order.setCustomerID(rs.getInt("customerID"));
        order.setTotalCost(rs.getInt("totalCost"));
        return order;
    }

    private static OrderDetail extractOrderDetailFromResultSet(ResultSet rs) throws SQLException {
        OrderDetail detail = new OrderDetail();
        detail.setId( rs.getInt("id") );
        detail.setOrderId( rs.getInt("orderID") );
        detail.setQuantity(rs.getInt("quantity"));
        detail.setTotalCost(rs.getInt("totalCost"));
        detail.setShoesDetailId(rs.getInt("shoesDetailID"));
        return detail;
    }
}
