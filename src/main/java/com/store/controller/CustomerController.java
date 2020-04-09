package com.store.controller;

import com.store.model.Customer;
import com.store.service.CustomerService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Customer")
public class CustomerController extends HttpServlet {

    private CustomerService customerService;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            try {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/shopDB");
            } catch (NamingException e) {
                JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
            }
            if(dataSource == null){
                throw new SQLException("Can't get DataSource");
            }
            else customerService = new CustomerService(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");

            switch (theCommand) {
                case "LIST":
                    listCustomers(request, response);
                    break;
                case "ADD":
                    addCustomer(request, response);
                    break;
                case "LOAD":
                    //todo
                    break;
                case "UPDATE":
                    //todo
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ErrorPage.jsp");
                    dispatcher.forward(request, response);
            }
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Customer> customers = customerService.getCustomers();
        request.setAttribute("CUSTOMER_LIST", customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Customer.jsp");
        dispatcher.forward(request, response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read student info from form data

        String name = request.getParameter("name");

        String tempDay = request.getParameter("dateOfBirth").substring(0,2);
        String tempMonth = request.getParameter("dateOfBirth").substring(3,5);
        String tempYear = request.getParameter("dateOfBirth").substring(6);
        String tempDate = tempYear + "-" + tempMonth + "-" +tempDay;

        Date dateOfBirth = Date.valueOf(tempDate);
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender").equals("on") ? "Nam" : "Nữ";
        String password = request.getParameter("password");

        // create a new student object
        Customer newCus = new Customer(name, dateOfBirth, email, phone, gender, password);

        // add the student to the database
        customerService.addCustomer(newCus);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }




}
