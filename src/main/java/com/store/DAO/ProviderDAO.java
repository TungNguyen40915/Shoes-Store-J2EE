package com.store.DAO;

import com.store.model.Provider;
import com.store.model.Sale;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProviderDAO {
    private static Connection connection;

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
