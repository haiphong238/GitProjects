package com.fieldomobify.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fieldomobify.backend.model.Job;

public interface JobRepository  extends MongoRepository<Job, String> {
	List<Job> findByOrganization(String organization);

}
