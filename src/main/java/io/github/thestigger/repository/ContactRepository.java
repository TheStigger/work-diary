package io.github.thestigger.repository;

import io.github.thestigger.entity.Contact;
import io.github.thestigger.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MongoDB Repository for Contact entity.
 */
@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    List<Contact> findByCompanyAndDirector(Organization company, boolean isDirector);
}
