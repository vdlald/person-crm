package com.vladislav.crm.controllers.impl;

import com.vladislav.crm.controllers.UserLeadController;
import com.vladislav.crm.controllers.requesthandlers.leads.ReadLeadRequestHandler;
import com.vladislav.crm.controllers.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/leads")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLeadControllerImpl implements UserLeadController {

    private final ReadLeadRequestHandler readLeadRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId)")
    public EntityModel<ReadLeadResponse> readLead(@PathVariable("id") Long leadId) {
        return readLeadRequestHandler.handle(leadId);
    }
}
