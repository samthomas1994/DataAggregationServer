package uk.ac.bath.classes;

import org.springframework.mock.staticmock.MockStaticEntityMethods;

import javax.persistence.*;

/**
 * Created by Sam on 03/02/2016.
 */

@Entity
@Table(name = "USERDETAILS")
public class UserDetails {

    private Integer id = null;
    private String firstname = null;
    private String lastname = null;
    private String username = null;
    private String password = null;

    public UserDetails() {

    }

    public UserDetails(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}
