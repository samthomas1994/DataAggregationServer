package uk.ac.bath.Web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uk.ac.bath.classes.Activity;
import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.controller.Controller_Add;
import uk.ac.bath.controller.Controller_Get;
import uk.ac.bath.hibernate.AutowiredDatabase;
import uk.ac.bath.utils.ParseCSV;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sam on 11/02/2016.
 */
@CrossOrigin(origins = "http://people.bath.ac.uk")
@RestController
public class WebServer {

    @Autowired
    AutowiredDatabase database;

    @Autowired
    Controller_Add add;

    @Autowired
    Controller_Get get;

    @RequestMapping("/test")
    public UserDetails test() {
        System.out.println("Web method called");
        UserDetails userDetails = new UserDetails("First name", "Last name", "Username", "Password");
        return userDetails;
    }


    @RequestMapping(value = "/userFromUsernameAndPassword", method = RequestMethod.POST)
    public List<UserDetails> userFromUsernameAndPassword(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        List<UserDetails> userDetails = database.getDatabase().userFromUsernameAndPassword(username, password);
        return userDetails;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public List<UserDetails> login(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        List<UserDetails> userDetails = add.login(username, password);
        return userDetails;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam(value="firstname") String firstname, @RequestParam(value="lastname") String lastname,
                          @RequestParam(value="username") String username, @RequestParam(value="password") String password) throws Exception {
        String success = add.addUser(firstname, lastname, username, password);
        return toJson(success);
    }

    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    public String addActivity(@RequestParam(value="source") String source, @RequestParam(value="category") String category,
                              @RequestParam(value="type") String type, @RequestParam(value="units") String units,
                              @RequestParam(value="username") String username) throws Exception {
        String success = add.addActivityWithNullEvent(source, category, type, units, username);
        return toJson(success);
    }

    @RequestMapping("/activitiesFromUser")
    public List<Activity> activitiesFromUser(@RequestParam(value="username") String username) {
        UserDetails user = database.getDatabase().userFromUsername(username);
        List<Activity> activities = database.getDatabase().activitiesFromUser(user);
        return activities;
    }

    @RequestMapping(value = "/eventsInLastMonth", method = RequestMethod.POST)
    public List activitiesFromUser(@RequestParam(value="username") String username, @RequestParam(value="activity") Long activityId) {
        UserDetails user = database.getDatabase().userFromUsername(username);
        Activity activity = database.getDatabase().activityFromId(activityId);
        List events = get.eventsForActivityAndUserInLastMonth(user, activity);
        return events;
    }

    @RequestMapping(value = "/uploadCSV", method = RequestMethod.POST)
    @ResponseBody
    public String csvUpload(MultipartHttpServletRequest request)
            throws Exception {

        Iterator<String> it = request.getFileNames();
        MultipartFile multiFile = request.getFile(it.next());
        String username = request.getParameter("username");
        String activity_id = request.getParameter("activity_id");
        ParseCSV csvParser = new ParseCSV(database);
        try {
            byte[] bytes = multiFile.getBytes();
            Long activity_id_value = Long.valueOf(activity_id).longValue();

            Boolean success = csvParser.importCSV(bytes, username, activity_id_value);
            if(!success) {
                throw new Exception("Error while loading the file: error when parsing the csv file");
            }
        } catch (NumberFormatException nf) {
            nf.printStackTrace();
            throw new Exception("Error while loading the file: activity or user selection error");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error while loading the file");
        }
        return toJson("File Uploaded successfully.");
    }

    public String toJson(Object data)
    {
        ObjectMapper mapper=new ObjectMapper();
        StringBuilder builder=new StringBuilder();
        try {
            builder.append(mapper.writeValueAsString(data));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return builder.toString();
    }
}

