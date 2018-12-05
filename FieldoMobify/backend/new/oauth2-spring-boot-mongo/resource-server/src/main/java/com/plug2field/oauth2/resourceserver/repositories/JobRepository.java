package com.plug2field.oauth2.resourceserver.repositories;

import com.plug2field.oauth2.resourceserver.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {
    List<Job> findByOrganization(String organization);

}
