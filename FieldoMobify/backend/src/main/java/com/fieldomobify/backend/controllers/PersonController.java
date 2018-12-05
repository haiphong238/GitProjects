package com.fieldomobify.backend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fieldomobify.backend.model.Account;
import com.fieldomobify.backend.model.Person;
import com.fieldomobify.backend.repositories.PersonRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@PostMapping("/people")
	public Person createPerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}

	@GetMapping(value = "/people/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable("id") String id) {
		return personRepository.findById(id).map(person -> ResponseEntity.ok().body(person))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/people/{id}")
	public ResponseEntity<Account> updatePerson(@PathVariable("id") String id, @Valid @RequestBody Person person) {
		personRepository.save(person);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/people/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable("id") String id) {
		return personRepository.findById(id).map(person -> {
			personRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/location/{locationId}/people")
	public List<Person> getPeopleByLocation(@PathVariable("locationId") String locationId) {
		return personRepository.findByLocationId(locationId);
	}
}
