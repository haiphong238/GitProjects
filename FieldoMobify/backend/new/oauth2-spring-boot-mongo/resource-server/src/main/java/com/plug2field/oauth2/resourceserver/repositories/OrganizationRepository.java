package com.plug2field.oauth2.resourceserver.repositories;

import com.plug2field.oauth2.resourceserver.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
    Optional<Organization> findById(String id);
    void deleteById(String id);
}
