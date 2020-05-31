package com.store.DAO;

import com.store.RequestModel.AddressAddModel;
import com.store.RequestModel.UpdateAddressModel;
import com.store.model.*;
import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    private static Connection connection;

    public static List<Address> getCustomerAddressByUserName(String username){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        List<Address> list = new ArrayList<Address>();
        int id = CustomerDAO.getCustomerIdByUserName(username);

        try {
            Statement stmt = connection.createStatement();
            String query = "select * from Address Where customerID = " + id;
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                list.add(extractCustomerAddressFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static Address getAddressById(int id){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select * from Address Where id = " + id;
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return extractCustomerAddressFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new Address();
    }

    public static List<Address> addAddress(String username, AddressAddModel addressAddModel){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        int id = CustomerDAO.getCustomerIdByUserName(username);

        try {
            String query = "INSERT INTO Address (customerID, city, district, ward, street, recipientName, recipientPhoneNumber) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,String.valueOf(id));
            preparedStatement.setString(2,String.valueOf(addressAddModel.getCity()));
            preparedStatement.setString(3,String.valueOf(addressAddModel.getDistrict()));
            preparedStatement.setString(4,String.valueOf(addressAddModel.getWard()));
            preparedStatement.setString(5,String.valueOf(addressAddModel.getStreet()));
            preparedStatement.setString(6,String.valueOf(addressAddModel.getName()));
            preparedStatement.setString(7,String.valueOf(addressAddModel.getPhoneNumber()));


            int row = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return getCustomerAddressByUserName(username);
    }

    public static List<Address> updateAddress(String username, UpdateAddressModel updateAddressModel){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        int id = CustomerDAO.getCustomerIdByUserName(username);

        try {
            String query = "UPDATE address SET city=?,district=?,ward=?,street=?,recipientName=?,recipientPhoneNumber=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,String.valueOf(updateAddressModel.getCity()));
            preparedStatement.setString(2,String.valueOf(updateAddressModel.getDistrict()));
            preparedStatement.setString(3,String.valueOf(updateAddressModel.getWard()));
            preparedStatement.setString(4,String.valueOf(updateAddressModel.getStreet()));
            preparedStatement.setString(5,String.valueOf(updateAddressModel.getName()));
            preparedStatement.setString(6,String.valueOf(updateAddressModel.getPhoneNumber()));
            preparedStatement.setString(7,String.valueOf(updateAddressModel.getId()));

            int row = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return getCustomerAddressByUserName(username);
    }

    public static CustomerOrder handleOrder(String username, int addressId){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        int orderId =  -1;
        List<Integer> insertedId = new ArrayList<Integer>();

        int userId = CustomerDAO.getCustomerIdByUserName(username);
        try {
            orderId = createOrder(connection,userId,1,1);
            insertedId = createOrderItem(connection,orderId,userId);
        } catch (Exception ex) {
            ex.printStackTrace();
            handleRollback(connection, orderId, insertedId);
        }
        return getOrderById(orderId);
    }

    private static int createOrder(Connection connection, int customerId, int saleId, int addressId){
        String query = "INSERT INTO CustomerOrder (customerID,saleID,status,paymentStatus,deliveryAddress,recipientName,recipientPhoneNumber) VALUES (?,?,?,?,?,?,?)";
        Address address = getAddressById(addressId);
        String deliveryAddress = address.getStreet().concat(" ").concat(address.getWard()).
                                                     concat(" ").concat(address.getDistrict()).
                                                     concat(" ").concat(address.getCity());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,String.valueOf(customerId));
            preparedStatement.setString(2,String.valueOf(saleId));
            preparedStatement.setString(3,"Queueing");
            preparedStatement.setString(4,"No Payment");
            preparedStatement.setString(5,String.valueOf(deliveryAddress));
            preparedStatement.setString(6,String.valueOf(address.getRecipientName()));
            preparedStatement.setString(7,String.valueOf(address.getRecipientPhoneNumber()));

            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected == 1)
            {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next())
                    return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private static List<Integer> createOrderItem(Connection connection, int orderId, int customerId){
        String query = "INSERT INTO OrderItem(orderId, stockID,amount,pricePerUnit) VALUES (?,?,?,?);";
        List<CartItem> cartItems = CartDAO.getAllCartItemsByCartId(CartDAO.getCartIdByUserId(customerId));
        List<Integer> insertedId = new ArrayList<Integer>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            Shoes shoes = new Shoes();
            for (CartItem item: cartItems) {
                preparedStatement.setString(1,String.valueOf(orderId));
                shoes = ShoesDAO.getShoesById(StockDAO.getStockById(item.getStockID()).getShoesID());
                preparedStatement.setString(2,String.valueOf(item.getStockID()));
                preparedStatement.setString(3,String.valueOf(item.getAmount()));
                preparedStatement.setString(4,String.valueOf(shoes.getPrice()));

                int rowAffected = preparedStatement.executeUpdate();
                if(rowAffected == 1)
                {
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if(rs.next())
                        insertedId.add(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return insertedId;
    }

    private static void handleRollback(Connection connection, int orderId, List<Integer> insertedId){
        String query = "DELETE FROM CustomerOrder WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,String.valueOf(orderId));
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected == 1)
            {
                query = "DELETE FROM OrderItem WHERE id = ?";
                for (int id: insertedId) {
                    preparedStatement.setString(1,String.valueOf(id));
                    rowAffected = preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static CustomerOrder getOrderById(int id){
        try {
            Statement stmt = connection.createStatement();
            String query = "select * from CustomerOrder Where id = " + id;
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return extractOrderFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<OrderItem> getOrderItemsByOrderId(int id){
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        try {
            Statement stmt = connection.createStatement();
            String query = "select * from OrderItem Where orderID = " + id;
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                orderItems.add(extractOrderItemFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orderItems;
    }

    public static List<CustomerOrder> getOrderListByCustomer(String username){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        List<CustomerOrder> list = new ArrayList<CustomerOrder>();
        int id = CustomerDAO.getCustomerIdByUserName(username);
        try {
            Statement stmt = connection.createStatement();
            String query = "select * from CustomerOrder Where customerID = " + id;
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                list.add(extractOrderFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private static Address extractCustomerAddressFromResultSet(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setCity(rs.getString("city"));
        address.setDistrict(rs.getString("district"));
        address.setWard(rs.getString("ward"));
        address.setStreet(rs.getString("street"));
        address.setCustomerID(rs.getInt("customerID"));
        address.setRecipientName(rs.getString("recipientName"));
        address.setRecipientPhoneNumber(rs.getString("recipientPhoneNumber"));
        return address;
    }

    private static CustomerOrder extractOrderFromResultSet(ResultSet rs) throws SQLException {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setId(rs.getInt("id"));
        customerOrder.setConfirmDate(rs.getDate("confirmDate"));
        customerOrder.setCustomerID(rs.getInt("customerId"));
        customerOrder.setDeliveryAddress(rs.getString("deliveryAddress"));
        customerOrder.setDeliveryDate(rs.getDate("deliveryDate"));
        customerOrder.setId(rs.getInt("id"));
        customerOrder.setOrderDate(rs.getDate("orderDate"));
        customerOrder.setStatus(rs.getString("status"));
        customerOrder.setPaymentStatus(rs.getString("paymentStatus"));
        customerOrder.setSaleID(rs.getInt("saleID"));
        customerOrder.setRecipientName(rs.getString("recipientName"));
        customerOrder.setRecipientPhoneNumber(rs.getString("recipientPhoneNumber"));
        return customerOrder;
    }

    private static OrderItem extractOrderItemFromResultSet(ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();
        item.setId(rs.getInt("id"));
        item.setAmount(rs.getInt("amount"));
        item.setOrderID(rs.getInt("orderId"));
        item.setPricePerUnit(rs.getInt("pricePerUnit"));
        item.setStockID(rs.getInt("stockId"));
        return item;
    }
}
