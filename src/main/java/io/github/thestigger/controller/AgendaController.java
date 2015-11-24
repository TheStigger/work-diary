package io.github.thestigger.controller;

import io.github.thestigger.service.EventService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

/**
 * Agenda Controller.
 * <p>
 * Used for UI manipulations.
 */
@ManagedBean
@ViewScoped
public class AgendaController {

    @ManagedProperty("#{eventService}")
    private EventService service;
}
