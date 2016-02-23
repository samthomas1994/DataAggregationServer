package uk.ac.bath.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.bath.classes.Activity;
import uk.ac.bath.classes.EventInfo;
import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.hibernate.AutowiredDatabase;
import uk.ac.bath.hibernate.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sam on 01/02/2016.
 */
@Controller
public class ParseCSV {

    AutowiredDatabase database;

    public ParseCSV() {

    }

    public ParseCSV(AutowiredDatabase database) {
        this.database = database;
    }

    public boolean importCSV(byte[] bytes, Long user_id, Long activity_id) {
        UserDetails user = database.getDatabase().userFromId(user_id);
        Activity activity = database.getDatabase().activityFromId(activity_id);
//        try {
//            FileReader csvFile = new FileReader(file);
            if(!readCSV(user, activity, bytes)) {
                return false;
            }
//        } catch(FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        }
        return true;
    }

    public boolean readCSV(UserDetails user, Activity activity, byte[] bytes) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
            br = new BufferedReader(new InputStreamReader(byteArray));
            //br = new BufferedReader(csvFile);
            while ((line = br.readLine()) != null) {

                //Don't import headers line
                if(line.contains("Date")) {
                    continue;
                }

                EventInfo event = new EventInfo();
                event.setUserDetails(user);
                event.setActivity(activity);

                //Use comma as separator
                String[] items = line.split(cvsSplitBy);

                //Date
                String stringDate = items[0];
                DateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
                Date date = format.parse(stringDate);
                java.sql.Date sDate = new java.sql.Date(date.getTime());
                event.setEvent_date(sDate);

                //Start Time
                DateFormat formatter = new SimpleDateFormat("HH:mm");
                java.sql.Time startTimeValue = new java.sql.Time(formatter.parse(items[1]).getTime());
                event.setStart_time(startTimeValue);

                //End Time
                java.sql.Time endTimeValue = new java.sql.Time(formatter.parse(items[2]).getTime());
                event.setEnd_time(endTimeValue);

                //Value
                Integer value = Integer.parseInt(items[3]);
                event.setValue(value);

                System.out.println(event);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
        return true;
    }
}
