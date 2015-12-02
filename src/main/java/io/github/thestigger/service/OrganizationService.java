package io.github.thestigger.service;

import io.github.thestigger.entity.Organization;
import io.github.thestigger.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for OrganizationRepository.
 */
@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository repository;

    public List<Organization> findAll() {
        return repository.findAll();
    }

    public void save(Organization o) {
        repository.save(o);
    }

    public void delete(Organization o) {
        repository.delete(o);
    }

    public void getById(String id) {
        repository.findOne(id);
    }
}
