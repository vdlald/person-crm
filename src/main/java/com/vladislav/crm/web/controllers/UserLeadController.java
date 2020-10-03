package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.CreateLeadRequest;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface UserLeadController {
    EntityModel<ReadLeadResponse> readLead(Long leadId);

    EntityModel<ReadLeadResponse> createLead(CreateLeadRequest request);

    ResponseEntity<Void> deleteLead(Long leadId);
}
