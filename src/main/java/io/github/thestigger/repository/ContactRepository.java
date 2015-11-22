package io.github.thestigger.repository;

import io.github.thestigger.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.inject.Named;

/**
 * Created by maxim on 11/22/15.
 */
@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
}
