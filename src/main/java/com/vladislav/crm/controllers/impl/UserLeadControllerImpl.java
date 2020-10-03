package com.vladislav.crm.controllers.impl;

import com.vladislav.crm.controllers.UserLeadController;
import com.vladislav.crm.controllers.requesthandlers.leads.DeleteLeadRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.leads.ReadLeadRequestHandler;
import com.vladislav.crm.controllers.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/leads")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLeadControllerImpl implements UserLeadController {

    private final ReadLeadRequestHandler readLeadRequestHandler;
    private final DeleteLeadRequestHandler deleteLeadRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId)")
    public EntityModel<ReadLeadResponse> readLead(@PathVariable("id") Long leadId) {
        return readLeadRequestHandler.handle(leadId);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteLead(
            @PathVariable("id") Long leadId
    ) {
        return deleteLeadRequestHandler.handle(leadId);
    }
}
