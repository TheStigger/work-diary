package io.github.thestigger.controller;

import io.github.thestigger.entity.Event;
import io.github.thestigger.service.EventService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Agenda Controller.
 * <p>
 * Used for UI manipulations.
 */
@ManagedBean
@ViewScoped
public class AgendaController implements Serializable {

    @ManagedProperty("#{eventService}")
    private EventService service;

    private List<Event> events;
    private Map<String, List<Event>> eventsMap;

    @PostConstruct
    public void init() {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 7);
        events = service.findByStartDateBetween(startDate.getTime(), endDate.getTime());

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Event event : events) {
            String truncatedDate = null;
            truncatedDate = dateFormat.format(event.getStartDate());
            if (eventsMap == null) {
                eventsMap = new TreeMap<>();
            }
            if (!eventsMap.containsKey(truncatedDate)) {
                eventsMap.put(truncatedDate, new ArrayList<>());
            }
            eventsMap.get(truncatedDate).add(event);
        }
        System.out.println(eventsMap);
    }

    public EventService getService() {
        return service;
    }

    public void setService(EventService service) {
        this.service = service;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Map<String, List<Event>> getEventsMap() {
        return eventsMap;
    }

    public void setEventsMap(Map<String, List<Event>> eventsMap) {
        this.eventsMap = eventsMap;
    }
}
