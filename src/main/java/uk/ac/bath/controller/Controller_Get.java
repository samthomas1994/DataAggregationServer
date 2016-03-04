package uk.ac.bath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uk.ac.bath.classes.Activity;
import uk.ac.bath.classes.EventInfo;
import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.hibernate.AutowiredDatabase;
import uk.ac.bath.utils.GraphData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Sam on 03/02/2016.
 */
@Controller
public class Controller_Get {

    @Autowired
    AutowiredDatabase database;

    public Controller_Get() {

    }

    public List eventsForActivityAndUserInLastMonth(UserDetails user, Activity activity) {
        DateFormat format = new SimpleDateFormat("dd MMM", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, -1);
        Date utilDate = cal.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        List<EventInfo> events = database.getDatabase().eventsFromUserActivityAndDate(user, activity, sqlDate);
        List<GraphData> data = new ArrayList<GraphData>();
        List<Date> days = getDaysBetweenDates(utilDate, new Date());
        boolean recorded;
        for(Date day: days) {
            recorded = false;
            for(EventInfo event: events) {
                String stringDate = format.format(day.getTime());
                String stringEventDate = format.format(event.getEvent_date().getTime());
                if(stringDate.equals(stringEventDate)) {
                    GraphData graphData = new GraphData(stringDate, event.getValue());
                    data.add(graphData);
                    recorded = true;
                    break;
                }
            }
            if(!recorded) {
                String stringDate = format.format(day.getTime());
                GraphData graphData = new GraphData(stringDate, 0);
                data.add(graphData);
            }
        }
        return data;
    }

    public List<Date> getDaysBetweenDates(Date startdate, Date enddate)
    {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
}
