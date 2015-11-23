package io.github.thestigger.controller;

import io.github.thestigger.entity.Contact;
import io.github.thestigger.service.ContactService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by maxim on 11/22/15.
 */
@ManagedBean
@ViewScoped
public class ContactListController {

    @ManagedProperty("#{contactService}")
    private ContactService contactService;
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @PostConstruct
    public void loadContacts() {
        contacts = contactService.findAll();
    }

    public ContactService getContactService() {
        return contactService;
    }

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }
}
