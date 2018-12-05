package com.fieldomobify.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fieldomobify.backend.model.Person;

public interface PersonRepository extends MongoRepository<Person, String>  {
	List<Person> findByLocationId(String locationId);
}
