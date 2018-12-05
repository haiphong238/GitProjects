package com.fieldomobify.backend.service.impl;

import com.fieldomobify.backend.model.Job;
import com.fieldomobify.backend.repositories.JobRepository;
import com.fieldomobify.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('admin')")
    public List<Job> findByOrganization(String organization) {
        return jobRepository.findByOrganization(organization);
    }
}
