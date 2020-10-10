package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.CreateLeadRequest;
import com.vladislav.crm.web.requests.UpdateLeadRequest;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface UserLeadController {
    EntityModel<ReadLeadResponse> readLead(Long leadId);

    EntityModel<ReadLeadResponse> createLead(CreateLeadRequest request);

    EntityModel<ReadLeadResponse> updateContact(Long leadId, UpdateLeadRequest request);

    ResponseEntity<Void> deleteLead(Long leadId);

    ResponseEntity<Void> moveLeadToAnotherStatus(Long leadId, Long statusId);
}
