package uk.ac.bath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uk.ac.bath.Security.Encryption;
import uk.ac.bath.Security.ValidInput;
import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.hibernate.AutowiredDatabase;

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

}
