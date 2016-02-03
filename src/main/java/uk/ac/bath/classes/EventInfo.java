package uk.ac.bath.classes;

import uk.ac.bath.classes.Activity;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Sam on 03/02/2016.
 */
public class EventInfo {

    private Integer id = null;
    private UserDetails userDetails = null;
    private Activity activity = null;
    private Date event_date = null;
    private Time start_time = null;
    private Time end_time = null;
    private Integer value = null;

    public EventInfo() {

    }

    public EventInfo(UserDetails userDetails, Activity activity, Date event_date, Time start_time, Time end_time, Integer value) {
        this.userDetails = userDetails;
        this.activity = activity;
        this.event_date = event_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.value = value;
    }

    public Activity getActivity() {
        return activity;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public Time getStart_time() {
        return start_time;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
