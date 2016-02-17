package uk.ac.bath.hibernate;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Sam on 16/02/2016.
 */
@Component
public class AutowiredDatabase {

    public AutowiredDatabase() {

    }

    public PersonDAO getDatabase() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        PersonDAO personDAO = context.getBean(PersonDAO.class);
        return personDAO;
    }
}
