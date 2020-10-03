package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;

public interface UserLeadController {
    EntityModel<ReadLeadResponse> readLead(Long leadId);
}
