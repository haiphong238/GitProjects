package com.fieldomobify.backend.service;

import com.fieldomobify.backend.model.Job;

import java.util.List;

public interface JobService {
    List<Job> findByOrganization(String organization);
}