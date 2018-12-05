package com.plug2field.oauth2.resourceserver.service;


import com.plug2field.oauth2.resourceserver.model.Job;

import java.util.List;

public interface JobService {
    List<Job> findByOrganization(String organization);
}