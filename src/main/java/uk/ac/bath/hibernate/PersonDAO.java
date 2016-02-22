package uk.ac.bath.hibernate;

import uk.ac.bath.classes.Activity;
import uk.ac.bath.classes.EventInfo;
import uk.ac.bath.classes.UserDetails;

import java.util.List;

/**
 * Created by Sam on 12/02/2016.
 */

public interface PersonDAO {

    void save(UserDetails user);
    void save(Activity activity);
    void save(EventInfo event);

    List<UserDetails> userList();
    List<Activity> activityList();
    List<EventInfo> eventList();

    UserDetails userFromId(Long id);

    Activity activityFromId(Long id);

    List<UserDetails> userFromUsernameAndPassword(String username, String password);

    List<Activity> activitiesFromUser(UserDetails user);

    List<EventInfo> eventsFromUserAndActivity(UserDetails user, Activity activity);
}

