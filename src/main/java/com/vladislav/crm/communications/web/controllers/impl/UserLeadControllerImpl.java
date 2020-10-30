package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.web.controllers.UserLeadController;
import com.vladislav.crm.communications.web.adapters.leads.*;
import com.vladislav.crm.communications.web.requests.CreateLeadRequest;
import com.vladislav.crm.communications.web.requests.UpdateLeadRequest;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/leads")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLeadControllerImpl implements UserLeadController {

    private final ReadLeadRequestHandlerAdapter readLeadRequestHandlerAdapter;
    private final CreateLeadRequestHandlerAdapter createLeadRequestHandlerAdapter;
    private final UpdateLeadRequestHandlerAdapter updateLeadRequestHandlerAdapter;
    private final DeleteLeadRequestHandlerAdapter deleteLeadRequestHandlerAdapter;
    private final MoveLeadToAnotherStatusRequestHandlerAdapter moveLeadToAnotherStatusRequestHandlerAdapter;
    private final AttachLeadToContactRequestHandlerAdapter attachLeadToContactRequestHandlerAdapter;
    private final GetAllLeadsInExcelRequestHandlerAdapter getAllLeadsInExcelRequestHandlerAdapter;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId)")
    public EntityModel<ReadLeadResponse> readLead(@PathVariable("id") Long leadId) {
        return readLeadRequestHandlerAdapter.handle(leadId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#request.statusId)")
    public EntityModel<ReadLeadResponse> createLead(
            @Valid @RequestBody CreateLeadRequest request
    ) {
        return createLeadRequestHandlerAdapter.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId)")
    public EntityModel<ReadLeadResponse> updateLead(
            @PathVariable("id") Long leadId,
            @Valid @RequestBody UpdateLeadRequest request
    ) {
        return updateLeadRequestHandlerAdapter.handle(Pair.of(leadId, request));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteLead(
            @PathVariable("id") Long leadId
    ) {
        return deleteLeadRequestHandlerAdapter.handle(leadId);
    }

    @Override
    @GetMapping("/{id}/moveTo/{statusId}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId) && " +
            "@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> moveLeadToAnotherStatus(
            @PathVariable("id") Long leadId,
            @PathVariable("statusId") Long statusId
    ) {
        return moveLeadToAnotherStatusRequestHandlerAdapter.handle(Pair.of(leadId, statusId));
    }

    @Override
    @GetMapping("/{id}/attachTo/{contactId}")
    @PreAuthorize("@userOwnsLeadAuthorization.hasAuthorization(#leadId) && " +
            "@userOwnsContactAuthorization.hasAuthorization(#contactId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> attachLeadToContact(
            @PathVariable("id") Long leadId,
            @PathVariable("contactId") Long contactId
    ) {
        return attachLeadToContactRequestHandlerAdapter.handle(Pair.of(leadId, contactId));
    }

    @Override
    @GetMapping(value = "/excel", produces = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void getAllLeadsInExcel(HttpServletRequest request, HttpServletResponse response) {
        getAllLeadsInExcelRequestHandlerAdapter.handle(Pair.of(request, response));
    }
}
