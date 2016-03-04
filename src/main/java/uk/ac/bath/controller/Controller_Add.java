package uk.ac.bath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.bath.Security.Encryption;
import uk.ac.bath.Security.ValidInput;
import uk.ac.bath.classes.Activity;
import uk.ac.bath.classes.EventInfo;
import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.hibernate.AutowiredDatabase;
import uk.ac.bath.repositories.Database_UserDetails;

import java.util.List;

/**
 * Created by Sam on 03/02/2016.
 */

@Controller
public class Controller_Add {

    @Autowired
    AutowiredDatabase database;

    public Controller_Add() {

    }

    /**********User************/

    public String addUser(String firstname, String lastname, String username, String password) throws Exception {
        ValidInput validInput = new ValidInput();
        if(firstname == null || firstname.equals("") || !validInput.validateName(firstname)) {
            throw new Exception("Invalid First Name");
        }
        if(lastname == null || lastname.equals("") || !validInput.validateName(lastname)) {
            throw new Exception("Invalid Last Name");
        }
        if(username == null || username.equals("") || !validInput.validateUsername(username)) {
            throw new Exception("Invalid Username");
        }
        if(database.getDatabase().userFromUsername(username) == null) {
            throw new Exception("Username already in use");
        }
        if(password == null || password.equals("")) {
            throw new Exception("Invalid password");
        }
        Encryption encryption = new Encryption();
        String encryptedPassword = encryption.encryptPassword(password);
        UserDetails user = new UserDetails(firstname, lastname, username, encryptedPassword);
        database.getDatabase().save(user);
        return "Account successfully created";
    }

    public List<UserDetails> login(String username, String password) {
        Encryption encryption = new Encryption();
        String encryptedPassword = encryption.encryptPassword(password);
        List<UserDetails> userDetails = database.getDatabase().userFromUsernameAndPassword(username, encryptedPassword);
        return userDetails;
    }

    /*********Activity*********/

    public String addActivity(String source, String category, String type, String units) throws Exception {
        Activity activity = new Activity(source, category, type, units);
        database.getDatabase().save(activity);
        return "Activity successfully created";
    }

    public String addActivityWithNullEvent(String source, String category, String type, String units, String username) throws Exception {
        Activity activity = new Activity(source, category, type, units);
        database.getDatabase().save(activity);
        addNullEvent(database.getDatabase().userFromUsername(username), activity);
        return "Activity successfully created";
    }

    /*********Event************/

    public String addNullEvent(UserDetails userDetails, Activity activity) {
        EventInfo eventInfo = new EventInfo(userDetails, activity, null, null, null, null);
        database.getDatabase().save(eventInfo);
        return "Event successfully created";
    }

}
