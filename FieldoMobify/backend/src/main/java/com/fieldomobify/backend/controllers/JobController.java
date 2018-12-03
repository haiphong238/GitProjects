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
import com.fieldomobify.backend.model.Job;
import com.fieldomobify.backend.repositories.JobRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class JobController {
	@Autowired
	private JobRepository jobRepository;
	
	@GetMapping("/jobs")
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
		
	}

	@PostMapping("/jobs")
	public Job createJob(@Valid @RequestBody Job job) {
		return jobRepository.save(job);
	}

	@GetMapping(value = "/jobs/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable("id") String id) {
		return jobRepository.findById(id).map(job -> ResponseEntity.ok().body(job))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/jobs/{id}")
	public ResponseEntity<Account> updateJob(@PathVariable("id") String id, @Valid @RequestBody Job job) {
		jobRepository.save(job);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/jobs/{id}")
	public ResponseEntity<?> deleteJob(@PathVariable("id") String id) {
		return jobRepository.findById(id).map(job -> {
			jobRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
		@GetMapping("/organization/{organizationId}/jobs")
		public List<Job> getJobsByOrganization(@PathVariable("organizationId") String organizationId) {
			return jobRepository.findByOrganization(organizationId);
		} 
}
