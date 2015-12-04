package io.github.thestigger.controller;

import io.github.thestigger.entity.Event;
import io.github.thestigger.service.EventService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Event List Controller.
 * <p>
 * Used for UI manipulations.
 */
@ManagedBean
@ViewScoped
public class EventListController implements Serializable {

    @ManagedProperty("#{eventService}")
    private EventService service;

    private List<Event> events;
    private Event event = new Event();
    private Date startDate;
    private Date endDate;
    private int rows = 30;

    @PostConstruct
    public void init() {
        events = service.findAll();
    }

    public void clear() {
        event = new Event();
    }

    public void save() {
        service.save(event);
        event = new Event();
        events = service.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Event saved!", null));
    }

    public void remove(Event event) {
        service.delete(event);
        events = service.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Event removed!", null));
    }

    public void filter() {
        if (startDate == null && endDate == null) {
            events = service.findAll();
        } else if (startDate != null && endDate != null) {
            events = service.findByStartDateBetween(startDate, endDate);
        } else if (startDate != null) {
            events = service.findByStartDateGreaterThan(startDate);
        } else {
            events = service.findByStartDateLessThan(endDate);
        }
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
