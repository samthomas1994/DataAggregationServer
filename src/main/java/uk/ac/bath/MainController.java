package uk.ac.bath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import uk.ac.bath.Web.WebServer;
import uk.ac.bath.classes.Activity;
import uk.ac.bath.classes.EventInfo;
import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.hibernate.AutowiredDatabase;
import uk.ac.bath.hibernate.PersonDAO;
import uk.ac.bath.utils.CreateTables;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by Sam on 05/02/2016.
 */
@Controller
public class MainController implements CommandLineRunner {

    @Autowired
    AutowiredDatabase database;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public void test() {


        List<UserDetails> list = database.getDatabase().userList();

        for(UserDetails p : list){
            System.out.println("User List: "+p);
        }

        List<Activity> list2 = database.getDatabase().activityList();

        for(Activity p2 : list2){
            System.out.println("Activity List: "+p2);
        }

        List<EventInfo> list3 = database.getDatabase().eventList();

        System.out.println(list3);

        for(EventInfo p3 : list3){
            System.out.println("Event List: "+p3);
        }
        //close resources
        //context.close();
    }

    @Override
    public void run(String... strings) throws Exception {
        test();
    }
}
