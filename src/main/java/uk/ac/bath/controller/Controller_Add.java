package uk.ac.bath.controller;

import org.springframework.stereotype.Component;
import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.database.Database_UserDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Sam on 03/02/2016.
 */

@Component
public class Controller_Add {

    private Database_UserDetails userDb;

    public Database_UserDetails getUserDb() {
        return userDb;
    }

    public void setUserDb(Database_UserDetails userDb) {
        this.userDb = userDb;
    }

    public void addUser(UserDetails user) {
        getUserDb().insert(user);
    }

    public void listUsers() {
        List<UserDetails> users = getUserDb().selectAll();
        for(int i = 0; i < users.size(); i++) {
            UserDetails user = users.get(i);

            //Display values
            System.out.print("ID: " + user.getId());
            System.out.print(", First: " + user.getFirstname());
            System.out.print(", Last: " + user.getLastname());
            System.out.print(", Username: " + user.getUsername());
            System.out.println(", Password: " + user.getPassword());
        }
    }

    //    Connection conn = null;
//    Statement stmt = null;
//
//    public Controller_Add(Connection conn) {
//        this.conn = conn;
//    }
//
//    public void addUser(uk.ac.bath.classes.UserDetails userDetails) {
//        try {
//            stmt = conn.createStatement();
//            String sql = "INSERT INTO USERDETAILS "
////                    "(firstname, lastname, username, password)"
//                    + "VALUES ("
//                    + userDetails.getId() + ", "
//                    + userDetails.getFirstname() + ", "
//                    + userDetails.getLastname() + ", "
//                    + userDetails.getUsername() + ", "
//                    + userDetails.getPassword() + ")";
//
//            stmt.executeUpdate(sql);
//        } catch (SQLException sqle) {
//            sqle.printStackTrace();
//        }
//
//    }
}
