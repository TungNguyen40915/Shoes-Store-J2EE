package com.store.DAO;

import com.store.model.*;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {

    public static Status getStatusById(int id) {

        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM status where id="+Integer.toString(id));
            while(rs.next())
            {
                return extractStatusFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Status> getAllStatus() {
        List<Status> status = new ArrayList<Status>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM status");
            while(rs.next())
            {
                Status stas = extractStatusFromResultSet(rs);
                status.add(stas);
            }
            return status;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static Status extractStatusFromResultSet(ResultSet rs) throws SQLException {
        Status status = new Status();
        status.setId( rs.getInt("id") );
        status.setName( rs.getString("name") );
        return status;
    }

}
