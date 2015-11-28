package io.github.thestigger.controller;

import io.github.thestigger.entity.Contact;
import io.github.thestigger.service.ContactService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Contact List Controller.
 *
 * Used for UI manipulations.
 */
@ManagedBean
@ViewScoped
public class ContactListController implements Serializable {

    @ManagedProperty("#{contactService}")
    private ContactService contactService;
    private List<Contact> contacts;
    private Contact contact = new Contact();

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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void save() {
        contactService.save(contact);
        contact = new Contact();
        contacts = contactService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contact saved!", null));
    }

    public void remove(Contact contact) {
        contactService.delete(contact);
        contacts = contactService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contact removed!", null));
    }

    public void clear() {
        contact = new Contact();
    }
}
