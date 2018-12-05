package com.plug2field.oauth2.resourceserver.controllers;

import com.plug2field.oauth2.resourceserver.model.Account;
import com.plug2field.oauth2.resourceserver.model.Job;
import com.plug2field.oauth2.resourceserver.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
//        return jobService.findAll();
        return null;
    }

    @PostMapping("/jobs")
    public Job createJob(@Valid @RequestBody Job job) {
//        return jobService.save(job);
        return null;
    }

    @GetMapping(value = "/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") String id) {
//        return jobService.findById(id).map(job -> ResponseEntity.ok().body(job))
//                .orElse(ResponseEntity.notFound().build());
        return null;
    }

    @PutMapping(value = "/jobs/{id}")
    public ResponseEntity<Account> updateJob(@PathVariable("id") String id, @Valid @RequestBody Job job) {
//        jobService.save(job);
//        return ResponseEntity.ok().build();
        return null;
    }

    @DeleteMapping(value = "/jobs/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable("id") String id) {
//        return jobService.findById(id).map(job -> {
//            jobService.deleteById(id);
//            return ResponseEntity.ok().build();
//        }).orElse(ResponseEntity.notFound().build());
        return null;
    }

    @GetMapping("/organization/{organizationId}/jobs")
    public List<Job> getJobsByOrganization(@PathVariable("organizationId") String organizationId) {
        return jobService.findByOrganization(organizationId);
    }
}
