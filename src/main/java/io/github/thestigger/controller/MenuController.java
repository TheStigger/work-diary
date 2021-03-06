package io.github.thestigger.controller;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * PrimeFaces Menu Controller.
 */
@ManagedBean
public class MenuController implements Serializable {

    private MenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        DefaultMenuItem item = new DefaultMenuItem("Calendar");
        item.setUrl("/");
        item.setIcon("ui-icon-calculator");
        model.addElement(item);

        item = new DefaultMenuItem("Agenda");
        item.setUrl("/agenda.xhtml");
        item.setIcon("ui-icon-script");
        model.addElement(item);

        item = new DefaultMenuItem("Events");
        item.setUrl("/events.xhtml");
        item.setIcon("ui-icon-calendar");
        model.addElement(item);

        item = new DefaultMenuItem("Contacts");
        item.setUrl("/contacts.xhtml");
        item.setIcon("ui-icon-contact");
        model.addElement(item);

        item = new DefaultMenuItem("Organizations");
        item.setUrl("/organizations.xhtml");
        item.setIcon("ui-icon-suitcase");
        model.addElement(item);
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}
