package uk.ac.bath.utils;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Sam on 03/02/2016.
 */
public class CreateTables {
    public CreateTables() {}

    public void create(Connection conn) {
        System.out.println("Creating table in given database...");

        Statement stmt = null;

        try {

            stmt = conn.createStatement();

            String sql = "CREATE TABLE USERDETAILS " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " username VARCHAR(255), " +
                    " password VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);

            String sql2 = "CREATE TABLE ACTIVITY " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " source VARCHAR(255), " +
                    " category VARCHAR(255), " +
                    " type VARCHAR(255), " +
                    " units VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql2);

            String sql3 = "CREATE TABLE EVENTINFO " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " user_id INTEGER, " +
                    " activity_id INTEGER, " +
                    " event_date DATE, " +
                    " start_time TIME, " +
                    " end_time TIME, " +
                    " value INTEGER, " +
                    " PRIMARY KEY ( id )," +
                    " FOREIGN KEY (user_id) REFERENCES USERDETAILS(id)," +
                    " FOREIGN KEY (activity_id) REFERENCES ACTIVITY(id))";

            stmt.executeUpdate(sql3);
            System.out.println("Created table in given database...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable(Connection conn, String name) {
        System.out.println("Dropping table " + name + " in given database...");

        Statement stmt = null;

        try {

            stmt = conn.createStatement();

            String sql = "DROP TABLE " + name + " ";


            stmt.executeUpdate(sql);
            System.out.println("Dropped table " + name + " in given database...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
