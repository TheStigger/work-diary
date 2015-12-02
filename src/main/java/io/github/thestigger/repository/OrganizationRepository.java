package io.github.thestigger.repository;

import io.github.thestigger.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * MongoDB Repository for Organization entity.
 */
@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {
}
