package com.store.DAO;

import com.store.model.Gender;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SizeDAO {

    public static int getSizeID(String sizeName){
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT id FROM Size Where name = \"" + sizeName + "\" AND deleteFlag = 0 LIMIT 1;";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
