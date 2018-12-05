package com.plug2field.oauth2.resourceserver.controllers;

import com.plug2field.oauth2.resourceserver.model.Organization;
import com.plug2field.oauth2.resourceserver.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
