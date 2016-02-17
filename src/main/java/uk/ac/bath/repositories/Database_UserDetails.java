package uk.ac.bath.repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import uk.ac.bath.classes.UserDetails;

import java.util.List;

/**
 * Created by Sam on 03/02/2016.
 */

public class Database_UserDetails {

    private SessionFactory sessionFactory;

    public Database_UserDetails() {

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(UserDetails user) {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public List<UserDetails> selectAll() {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UserDetails.class);
        List<UserDetails> users = (List<UserDetails>) criteria.list();
        session.getTransaction().commit();
        return users;
    }
}
