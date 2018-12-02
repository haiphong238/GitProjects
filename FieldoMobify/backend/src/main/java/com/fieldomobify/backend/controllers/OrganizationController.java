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

import com.fieldomobify.backend.model.Organization;
import com.fieldomobify.backend.repositories.OrganizationRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class OrganizationController {

	@Autowired
	private OrganizationRepository organizationRepository;
	
	@GetMapping("/organizations")
	public List<Organization> getAllOrganizations() {
		return organizationRepository.findAll();
		
	}

	@PostMapping("/organizations")
	public Organization createOrganization(@Valid @RequestBody Organization organization) {
		return organizationRepository.save(organization);
	}

	@GetMapping(value = "/organizations/{id}")
	public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") String id) {
		return organizationRepository.findById(id).map(organization -> ResponseEntity.ok().body(organization))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/organizations/{id}")
	public ResponseEntity<Organization> updateOrganization(@PathVariable("id") String id, @Valid @RequestBody Organization organization) {
		organizationRepository.save(organization);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/organizations/{id}")
	public ResponseEntity<?> deleteOrganization(@PathVariable("id") String id) {
		return organizationRepository.findById(id).map(organization -> {
			organizationRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
