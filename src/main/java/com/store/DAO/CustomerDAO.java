package com.store.DAO;

import com.store.RequestModel.CustomerUpdateModel;
import com.store.RequestModel.NewCustomerModel;
import com.store.model.Customer;
import com.store.model.User;
import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static Connection connection;

    public static int getCustomerIdByUserName(String username){
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "select Id from Customer Where userID = (Select Id from User Where username= \'"+ username + "\' limit 1);";
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

    public static Customer getCustomerInfomationByUsername(String username){
        if(connection == null)
            connection = ConnectionFactory.getConnection();

        try {
            int userId = getCustomerIdByUserName(username);
            Statement stmt = connection.createStatement();
            String query = "select * from Customer Where id = " + userId;
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return extractCustomerFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Customer updateCustomerInfomation(String username, CustomerUpdateModel customerUpdateModel){
        if(connection == null)
            connection = ConnectionFactory.getConnection();

        String SQL = "UPDATE Customer SET name= ?, dateOfBirth=?, email=?, phoneNumber=?, genderId=? where id=?";
        int id = getCustomerIdByUserName(username);

        try (PreparedStatement pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, String.valueOf(customerUpdateModel.getName()));
            pstmt.setString(2, String.valueOf(customerUpdateModel.getDateOfBirth()));
            pstmt.setString(3, String.valueOf(customerUpdateModel.getEmail()));
            pstmt.setString(4, String.valueOf(customerUpdateModel.getPhoneNumber()));
            pstmt.setString(5, String.valueOf(GenderDAO.getGenderID(customerUpdateModel.getGender())));
            pstmt.setString(6, String.valueOf(id));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return getCustomerInfomationByUsername(username);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public static Customer registerCustomer(NewCustomerModel newCustomerModel) throws Exception {
        if(connection == null)
            connection = ConnectionFactory.getConnection();

        String insertCustomer = "INSERT INTO Customer (name,dateOfBirth,email,phoneNumber,genderID,userID) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertCustomer, Statement.RETURN_GENERATED_KEYS)) {
            int user = UserDAO.insertUser(newCustomerModel.getEmail(), newCustomerModel.getPassword(), 1);

            if(user == 0)
                throw new Exception("Email has already registered");
            else if(user == 2)
                throw new Exception("Something went wrong");
            else if(user == 1) {
                pstmt.setString(1, String.valueOf(newCustomerModel.getName()));
                pstmt.setString(2, String.valueOf(newCustomerModel.getDateOfBirth()));
                pstmt.setString(3, String.valueOf(newCustomerModel.getEmail()));
                pstmt.setString(4, String.valueOf(newCustomerModel.getPhoneNumber()));
                pstmt.setString(5, String.valueOf(GenderDAO.getGenderID(newCustomerModel.getGender())));
                pstmt.setString(6, String.valueOf(UserDAO.getUserIdByUsername(newCustomerModel.getEmail())));
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    return getCustomerInfomationByUsername(newCustomerModel.getEmail());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }



    private static Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId( rs.getInt("id") );
        customer.setName( rs.getString("name") );
        customer.setDateOfBirth(rs.getDate("dateOfBirth"));
        customer.setEmail(rs.getString("email"));
        customer.setPhoneNumber(rs.getString("phoneNumber"));
        customer.setGenderID(rs.getInt("genderID"));
        //customer.setSize(rs.);
        return customer;
    }


}
