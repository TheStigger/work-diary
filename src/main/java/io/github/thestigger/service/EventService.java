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
        return repository.findAll();
    }

    public void save(Event e) {
        /*If event lasts all day, then set startDay hours to 0,
        and set endDay to the next day with hours set to 0*/
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
        repository.save(e);
    }

    public void delete(Event e) {
        repository.delete(e);
    }

    public void getById(String id) {
        repository.findOne(id);
    }

    public List<Event> findByStartDateBetween(Date startDate, Date endDate) {
        return repository.findByStartDateBetween(startDate, endDate);
    }
}
