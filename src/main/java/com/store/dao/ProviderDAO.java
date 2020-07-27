package com.store.dao;

import com.store.model.Provider;
import com.store.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProviderDAO {
    private static Connection connection;

    public static boolean insert(String name) {
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        int generatedKey = 0;
        try {
            String query = "INSERT INTO Provider (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,String.valueOf(name));

            int row = preparedStatement.executeUpdate();
            if (row>0) return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static List<Provider> getAllProviders(){
        List<Provider> list = new ArrayList<Provider>();
        if(connection == null)
            connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT id,name FROM provider where deleteFlag <> true";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                list.add(extractProviderFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private static Provider extractProviderFromResultSet(ResultSet rs) throws SQLException {
        Provider provider = new Provider();
        provider.setId(rs.getInt("id"));
        provider.setName(rs.getString("name"));
        return provider;
    }
}
