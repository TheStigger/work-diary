package io.github.thestigger.model;

import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;

/**
 * Schedule model with overridden addEvent() method.
 * <p>
 * This method don't override id in Event entity.
 */
public class StableScheduleModel extends DefaultScheduleModel {

    @Override
    public void addEvent(ScheduleEvent event) {
        getEvents().add(event);
    }
}