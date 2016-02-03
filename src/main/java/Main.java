import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.controller.Controller_Get;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Sam on 01/02/2016.
 */
public class Main {
    public static void main(String [] args)
    {
/*        uk.ac.bath.utils.ParseCSV csvParser = new uk.ac.bath.utils.ParseCSV();
        csvParser.importCSV();*/
        Connection conn = null;


        try
        {

            String url = "jdbc:mysql://mysql5host.bath.ac.uk/MFPIF";
            String user = "srt32";
            String password = "yusbEfn9kz";
            Class.forName ("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection (url, user, password);
            System.out.println ("Database connection established");
            UserDetails test = new UserDetails("Test", "Person", "TP", "1234");
//            Controller_Add add = new Controller_Add(conn);
//            add.addUser(test);
            Controller_Get get = new Controller_Get(conn);
            get.printUserDetails();

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
    }
}
