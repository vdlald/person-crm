package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface UserLeadController {
    EntityModel<ReadLeadResponse> readLead(Long leadId);

    ResponseEntity<Void> deleteLead(Long leadId);
}
