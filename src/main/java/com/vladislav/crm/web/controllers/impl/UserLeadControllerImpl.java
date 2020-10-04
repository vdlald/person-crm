package com.vladislav.crm.web.controllers.impl;

import com.vladislav.crm.web.controllers.UserLeadController;
import com.vladislav.crm.web.handlers.leads.CreateLeadRequestHandler;
import com.vladislav.crm.web.handlers.leads.DeleteLeadRequestHandler;
import com.vladislav.crm.web.handlers.leads.ReadLeadRequestHandler;
import com.vladislav.crm.web.handlers.leads.UpdateLeadRequestHandler;
import com.vladislav.crm.web.requests.CreateLeadRequest;
import com.vladislav.crm.web.requests.UpdateLeadRequest;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/leads")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLeadControllerImpl implements UserLeadController {

    private final ReadLeadRequestHandler readLeadRequestHandler;
    private final CreateLeadRequestHandler createLeadRequestHandler;
    private final UpdateLeadRequestHandler updateLeadRequestHandler;
    private final DeleteLeadRequestHandler deleteLeadRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId)")
    public EntityModel<ReadLeadResponse> readLead(@PathVariable("id") Long leadId) {
        return readLeadRequestHandler.handle(leadId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ReadLeadResponse> createLead(
            @Valid @RequestBody CreateLeadRequest request
    ) {
        return createLeadRequestHandler.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId)")
    public EntityModel<ReadLeadResponse> updateContact(
            @PathVariable("id") Long leadId,
            @Valid @RequestBody UpdateLeadRequest request
    ) {
        return updateLeadRequestHandler.handle(Pair.of(leadId, request));
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
