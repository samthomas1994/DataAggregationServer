package uk.ac.bath.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import uk.ac.bath.classes.Activity;
import uk.ac.bath.classes.EventInfo;
import uk.ac.bath.classes.UserDetails;

import java.sql.Date;
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
    public UserDetails userFromId(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        return (UserDetails)session.get(UserDetails.class, id);
    }

    @Override
    public Activity activityFromId(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        return (Activity) session.get(Activity.class, id);
    }

    @Override
    public UserDetails userFromUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UserDetails.class);
        criteria.add(Restrictions.eq("username", username));
        UserDetails user = (UserDetails) criteria.uniqueResult();
        session.getTransaction().commit();
        return user;
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

    @Override
    public List<Activity> activitiesFromUser(UserDetails user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(EventInfo.class);
        criteria.add(Restrictions.eq("userDetails", user));
        criteria.setProjection(Projections.distinct(Projections.property("activity")));
        List<Activity> activities = (List<Activity>) criteria.list();
        session.getTransaction().commit();
        return activities;
    }

    @Override
    public List<EventInfo> eventsFromUserAndActivity(UserDetails user, Activity activity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(EventInfo.class);
        criteria.add(Restrictions.eq("userDetails", user));
        criteria.add(Restrictions.eq("activity", activity));
        List<EventInfo> events = (List<EventInfo>) criteria.list();
        session.getTransaction().commit();
        return events;
    }

    @Override
    public List<EventInfo> eventsFromUserActivityAndDate(UserDetails user, Activity activity, Date startDate) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(EventInfo.class);
        criteria.add(Restrictions.eq("userDetails", user));
        criteria.add(Restrictions.eq("activity", activity));
        criteria.add(Restrictions.ge("event_date", startDate));
        List<EventInfo> events = (List<EventInfo>) criteria.list();
        session.getTransaction().commit();
        return events;
    }

}
