package io.github.thestigger.controller.helper;

import io.github.thestigger.entity.Contact;
import io.github.thestigger.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Helper class for converting Contact entity.
 */
@Service
public class ContactConverter implements Converter {

    @Autowired
    private ContactService contactService;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        System.out.println(value);
        if (value != null && value.trim().length() > 0) {
            try {
                return contactService.getById(value);
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Contact) object).getId());
        } else {
            return null;
        }
    }

    public ContactService getContactService() {
        return contactService;
    }

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }
}
