package io.github.thestigger.repository;

import io.github.thestigger.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * MongoDB Repository for Event entity.
 */
@Repository
public interface EventRepository extends MongoRepository<Event, String> {
}
