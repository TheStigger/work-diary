package io.github.thestigger.service;

import io.github.thestigger.entity.Contact;
import io.github.thestigger.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by maxim on 11/22/15.
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

    public void getById(String id) {
        repository.findOne(id);
    }
}
