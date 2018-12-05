package com.plug2field.oauth2.resourceserver.service.impl;

import com.plug2field.oauth2.resourceserver.model.Job;
import com.plug2field.oauth2.resourceserver.repositories.JobRepository;
import com.plug2field.oauth2.resourceserver.service.JobService;
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
