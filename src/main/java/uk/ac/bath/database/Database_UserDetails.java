package uk.ac.bath.database;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.bath.classes.UserDetails;

import java.util.List;

/**
 * Created by Sam on 03/02/2016.
 */
public class Database_UserDetails {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
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
