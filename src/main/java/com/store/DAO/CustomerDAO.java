package com.store.DAO;

import com.store.model.Customer;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static Connection connection;

    public static int getCustomerIdByUserName(String username){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select Id from Customer Where userID = (Select Id from ShoesStore.User Where username= \'"+ username + "\' limit 1);";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return rs.getInt("Id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private static Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId( rs.getInt("id") );
        customer.setName( rs.getString("name") );
        customer.setPhoneNumber(rs.getString("phoneNumber"));
        return customer;
    }


}
