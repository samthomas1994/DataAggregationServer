package uk.ac.bath.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import uk.ac.bath.classes.Activity;
import uk.ac.bath.classes.EventInfo;
import uk.ac.bath.classes.UserDetails;

import java.util.List;

/**
 * Created by Sam on 12/02/2016.
 */
public class PersonDAOImpl implements PersonDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(UserDetails user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
    }

    @Override
    public void save(Activity activity) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(activity);
        tx.commit();
        session.close();
    }

    @Override
    public void save(EventInfo event) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(event);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDetails> userList() {
        Session session = this.sessionFactory.openSession();
        List<UserDetails> userList = session.createQuery("from UserDetails").list();
        session.close();
        return userList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Activity> activityList() {
        Session session = this.sessionFactory.openSession();
        List<Activity> activityList = session.createQuery("from Activity").list();
        session.close();
        return activityList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EventInfo> eventList() {
        Session session = this.sessionFactory.openSession();
        List<EventInfo> eventList = session.createQuery("from EventInfo").list();
        session.close();
        return eventList;
    }

    @Override
    public List<UserDetails> userFromUsernameAndPassword(String username, String password) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UserDetails.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", password));
        List<UserDetails> users = (List<UserDetails>) criteria.list();
        session.getTransaction().commit();
        return users;
    }

}
