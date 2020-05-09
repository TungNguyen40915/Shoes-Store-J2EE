package com.store.DAO;

import com.store.model.Customer;
import com.store.model.User;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
            while(rs.next())
            {
                Customer cus = extractCustomerFromResultSet(rs);
                customers.add(cus);
            }
            return customers;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Customer getCustomerById(int id) {

        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer where id="+Integer.toString(id));
            while(rs.next())
            {
                return extractCustomerFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId( rs.getInt("id") );
        customer.setName( rs.getString("name") );
        customer.setAddress(rs.getString("address"));
        customer.setPhoneNumber(rs.getString("phoneNumber"));
        customer.setUserId(rs.getInt("userID"));;
        return customer;
    }


}
