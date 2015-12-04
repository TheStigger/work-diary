package io.github.thestigger.controller;

import io.github.thestigger.entity.Contact;
import io.github.thestigger.entity.Organization;
import io.github.thestigger.service.ContactService;
import io.github.thestigger.service.OrganizationService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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

    @ManagedProperty("#{organizationService}")
    private OrganizationService organizationService;

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

    public List<Organization> completeOrganization(String query) {
        return organizationService.findAll().stream()
                .filter(org -> org.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public char getOrganizationGroup(Organization org) {
        return org.getName().charAt(0);
    }

    public OrganizationService getOrganizationService() {
        return organizationService;
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
}
