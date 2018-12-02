package com.fieldomobify.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fieldomobify.backend.model.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, String> {

}
