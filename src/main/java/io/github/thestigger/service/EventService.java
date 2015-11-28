package io.github.thestigger.service;

import io.github.thestigger.entity.Event;
import io.github.thestigger.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        repository.save(e);
    }

    public void delete(Event e) {
        repository.delete(e);
    }

    public void getById(String id) {
        repository.findOne(id);
    }

    public List<Event> findByDatetimeBetween(Date startDate, Date endDate) {
        return repository.findByDatetimeBetween(startDate, endDate);
    }
}
