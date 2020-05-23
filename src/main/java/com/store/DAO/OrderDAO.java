package com.store.DAO;

import com.store.model.Address;
import com.store.model.Customer;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                list.add(extractCustomerFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }


    private static Address extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setCity(rs.getString("city"));
        address.setDistrict(rs.getString("district"));
        address.setWard(rs.getString("ward"));
        address.setStreet(rs.getString("street"));
        address.setCustomerID(rs.getInt("customerID"));
        return address;
    }
}
