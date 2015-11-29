package io.github.thestigger.service;

import io.github.thestigger.entity.Event;
import io.github.thestigger.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by maxim on 11/23/15.
 */
@Service
public class EventService {
    @Autowired
    private EventRepository repository;


    public List<Event> findAll() {
        List<Event> events = repository.findAll();
        events.sort((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()));
        return events;
    }

    public void save(Event e) {
        /*If event lasts all day, then set starting day hours to 0,
        and set ending day to the next day with hours set to 0*/
        if (e.isAllDay()) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Calendar date = Calendar.getInstance();
                date.setTime(dateFormat.parse(dateFormat.format(e.getStartDate())));
                e.setStartDate(date.getTime());

                date.add(Calendar.DATE, 1);
                e.setEndDate(date.getTime());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

        /*If endDate less than startDate, then set endDate equals to startDate*/
        if (e.getEndDate().compareTo(e.getStartDate()) < 0) {
            Calendar date = Calendar.getInstance();
            date.setTime(e.getStartDate());
            date.add(Calendar.MINUTE, 15);
            e.setEndDate(date.getTime());
        }
        repository.save(e);
    }

    public void delete(Event e) {
        repository.delete(e);
    }

    public void getById(String id) {
        repository.findOne(id);
    }

    public List<Event> findByStartDateBetween(Date startDate, Date endDate) {
        List<Event> events = repository.findByStartDateBetween(startDate, endDate);
        events.sort((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()));
        return events;
    }
}
