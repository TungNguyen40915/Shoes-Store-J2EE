package com.store.DAO;

import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShoesStyleDAO {
    public static int getStyleID(String styleName){
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT id FROM ShoesType Where name = \"" + styleName + "\" AND deleteFlag = 0 LIMIT 1;";
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

    public static String getStyleName(int id){
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT name FROM ShoesType Where id = \"" + id + "\" AND deleteFlag = 0 LIMIT 1;";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return rs.getString("name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
