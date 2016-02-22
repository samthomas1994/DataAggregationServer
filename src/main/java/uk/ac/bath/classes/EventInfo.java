package uk.ac.bath.classes;

import org.hibernate.annotations.ForeignKey;
import uk.ac.bath.classes.Activity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Sam on 03/02/2016.
 */
@Entity
@Table(name = "EVENTINFO")
public class EventInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserDetails userDetails;

    @ManyToOne
    @JoinColumn(name="activity_id")
    private Activity activity;

    @Column(name="event_date")
    private Date event_date;

    @Column(name="start_time")
    private Time start_time;

    @Column(name="end_time")
    private Time end_time;

    @Column(name="value")
    private Integer value;

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

    public Long getId() {
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

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return String.format(
                "EventInfo[id=%d, userDetails='%s', activity='%s', event_date='%s', start_time='%s', " +
                        "end_time='%s', value='%d']",
                id, userDetails, activity, event_date, start_time, end_time, value);
    }
}
