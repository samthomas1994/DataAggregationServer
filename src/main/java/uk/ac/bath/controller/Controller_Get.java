package uk.ac.bath.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Sam on 03/02/2016.
 */
public class Controller_Get {
    Connection conn = null;
    Statement stmt = null;

    public Controller_Get(Connection conn) {
        this.conn = conn;
    }

    public void printUserDetails() {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT id, firstname, lastname, username, password FROM USERDETAILS ";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", First: " + firstname);
                System.out.print(", Last: " + lastname);
                System.out.print(", Username: " + username);
                System.out.println(", Password: " + password);

            }
            rs.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
