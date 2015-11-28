package io.github.thestigger.repository;

import io.github.thestigger.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * MongoDB Repository for Event entity.
 */
@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    // TODO: rewrite to use correct query
    List<Event> findByStartDateBetween(Date startDate, Date endDate);
}
