package io.github.thestigger.service;

import io.github.thestigger.entity.Contact;
import io.github.thestigger.entity.Organization;
import io.github.thestigger.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Contact entity.
 */
@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;

    public List<Contact> findAll() {
        return repository.findAll();
    }

    public void save(Contact c) {
        repository.save(c);
    }

    public void delete(Contact c) {
        repository.delete(c);
    }

    public Contact getById(String id) {
        return repository.findOne(id);
    }

    public List<Contact> findDirectorsByCompany(Organization organization) {
        return repository.findByCompanyAndDirector(organization, true);
    }
}
