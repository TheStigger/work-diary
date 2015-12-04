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
public class OrganizationListController implements Serializable {

    @ManagedProperty("#{organizationService}")
    private OrganizationService organizationService;

    @ManagedProperty("#{contactService}")
    private ContactService contactService;

    private List<Organization> organizations;
    private Organization organization = new Organization();
    private List<Contact> selectedContacts;

    @PostConstruct
    public void loadContacts() {
        organizations = organizationService.findAll();
    }

    public void save() {
        organizationService.save(organization);
        for (Contact contact : contactService.findDirectorsByCompany(organization)) {
            contact.setDirector(false);
            contact.setCompany(null);
            contactService.save(contact);
        }
        for (Contact contact : selectedContacts) {
            contact.setCompany(organization);
            contact.setDirector(true);
            contactService.save(contact);
        }
        organization = new Organization();
        organizations = organizationService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Organization saved!", null));
    }

    public void remove(Contact contact) {
        organizationService.delete(organization);
        organizations = organizationService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Organization removed!", null));
    }

    public void clear() {
        organization = new Organization();
    }

    public List<Contact> completeContact(String query) {

        return contactService.findAll().stream()
                .filter(con -> (con.getName() + con.getSurname())
                        .toLowerCase()
                        .contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public char getContactGroup(Contact contact) {
        return contact.getSurname().charAt(0);
    }

    public OrganizationService getOrganizationService() {
        return organizationService;
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
        selectedContacts = contactService.findDirectorsByCompany(organization);
    }

    public ContactService getContactService() {
        return contactService;
    }

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    public List<Contact> getSelectedContacts() {
        return selectedContacts;
    }

    public void setSelectedContacts(List<Contact> selectedContacts) {
        this.selectedContacts = selectedContacts;
    }
}
