package uk.ac.bath.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;
import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.hibernate.AutowiredDatabase;
import uk.ac.bath.hibernate.PersonDAO;

import java.util.List;

/**
 * Created by Sam on 11/02/2016.
 */
@CrossOrigin(origins = "http://people.bath.ac.uk")
@RestController
public class WebServer {

    @Autowired
    AutowiredDatabase database;

    @RequestMapping("/test")
    public UserDetails test() {
        System.out.println("Web method called");
        UserDetails userDetails = new UserDetails("First name", "Last name", "Username", "Password");
        return userDetails;
    }


    @RequestMapping("/userFromUsernameAndPassword")
    public List<UserDetails> test (@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        List<UserDetails> userDetails = database.getDatabase().userFromUsernameAndPassword(username, password);
        return userDetails;
    }

}
