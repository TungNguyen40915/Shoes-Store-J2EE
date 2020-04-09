package com.store.service;

import com.store.model.Customer;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private DataSource dataSource;

    public CustomerService(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Customer> getCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "select * from Customer";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                //todo
            }

            System.out.println("45");
            return customers;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void addCustomer(Customer newCus) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            newCus.setPassword(hashPasssword(newCus.getPassword()));

            myConn = dataSource.getConnection();

            String sql = "insert into Customer "
                    + "(name, dateOfBirth, email, phoneNumber, gender, password) "
                    + "values (?, ?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the student
            myStmt.setString(1, newCus.getName());
            myStmt.setDate(2, newCus.getDateOfBirth());
            myStmt.setString(3, newCus.getEmail());
            myStmt.setString(4, newCus.getPhoneNumber());
            myStmt.setString(5, newCus.getGender());
            myStmt.setString(6, newCus.getPassword());
            // execute query
            myStmt.execute();

        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, null);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private String hashPasssword(String password){
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return "";
        }
    }

}
