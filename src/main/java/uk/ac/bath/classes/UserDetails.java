package uk.ac.bath.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.mock.staticmock.MockStaticEntityMethods;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sam on 03/02/2016.
 */

@Entity
@Table(name = "USERDETAILS")
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    protected UserDetails() {}

    public UserDetails(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

//    @Id
//    @GeneratedValue
//    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format(
                "UserDetails[id=%d, firstname='%s', lastname='%s', username='%s', password='%s']",
                id, firstname, lastname, username, password);
    }

}
