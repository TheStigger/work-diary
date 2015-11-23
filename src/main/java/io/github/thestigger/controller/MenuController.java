package io.github.thestigger.controller;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 * Created by maxim on 11/23/15.
 */
@ManagedBean
//@ViewScoped
public class MenuController {

    private MenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        // TODO resolve hardcoding problem
        DefaultMenuItem item = new DefaultMenuItem("Home");
        item.setUrl("/");
        item.setIcon("ui-icon-home");
        model.addElement(item);

        item = new DefaultMenuItem("Contacts");
        item.setUrl("/contacts.xhtml");
        item.setIcon("ui-icon-contact");
        model.addElement(item);
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}
