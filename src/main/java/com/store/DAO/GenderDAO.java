package com.store.DAO;

import com.store.model.Gender;
import com.store.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenderDAO {

    public static List<Gender> getAllGenders() {
        List<Gender> genders = new ArrayList<Gender>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM gender");
            while(rs.next())
            {
                Gender gendersTemp = extractGenderFromResultSet(rs);
                genders.add(gendersTemp);
            }
            return genders;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private static Gender extractGenderFromResultSet(ResultSet rs) throws SQLException {
        Gender gender = new Gender();
        gender.setId( rs.getInt("id") );
        gender.setName( rs.getString("name") );
        return gender;
    }


}
