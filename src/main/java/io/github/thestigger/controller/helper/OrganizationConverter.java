package io.github.thestigger.controller.helper;

import io.github.thestigger.entity.Organization;
import io.github.thestigger.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Helper class for converting Organization entity.
 */
@Service
public class OrganizationConverter implements Converter {

    @Autowired
    private OrganizationService service;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return service.getById(value);
            } catch (NumberFormatException e) {
                throw new ConverterException(
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Organization) object).getId());
        } else {
            return null;
        }
    }

    public OrganizationService getService() {
        return service;
    }

    public void setService(OrganizationService service) {
        this.service = service;
    }
}
