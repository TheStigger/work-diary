package io.github.thestigger.controller;

import io.github.thestigger.entity.Event;
import io.github.thestigger.model.StableScheduleModel;
import io.github.thestigger.service.EventService;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


/**
 * Calendar Controller.
 *
 * Used for UI manipulations.
 */
@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

    @ManagedProperty("#{eventService}")
    private EventService service;

    private ScheduleModel eventModel;

    private Event event = new Event();

    @PostConstruct
    public void init() {
        eventModel = new StableScheduleModel();
        for (ScheduleEvent event : service.findAll()) {
            eventModel.addEvent(event);
        }
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        System.out.println("add event");
        System.out.println(event);
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }

        service.save(event);
        event = new Event();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (Event) selectEvent.getObject();
        System.out.println(event);
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new Event();
        event.setStartDate((Date) selectEvent.getObject());
        event.setEndDate((Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
        System.out.println("event move ");
        service.save((Event) event.getScheduleEvent());
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
        System.out.println("event resize");
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public EventService getService() {
        return service;
    }

    public void setService(EventService service) {
        this.service = service;
    }
}