package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.web.controllers.UserContactsController;
import com.vladislav.crm.communications.web.handlers.contacts.*;
import com.vladislav.crm.communications.web.requests.CreateContactRequest;
import com.vladislav.crm.communications.web.requests.UpdateContactRequest;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactsControllerImpl implements UserContactsController {

    private final ReadUserContactsRequestHandler readUserContactsRequestHandler;
    private final ReadContactRequestHandler readContactRequestHandler;
    private final CreateContactRequestHandler createContactRequestHandler;
    private final UpdateContactRequestHandler updateContactRequestHandler;
    private final AttachContactToCompanyRequestHandler attachContactToCompanyRequestHandler;
    private final DeleteContactRequestHandler deleteContactRequestHandler;

    @Override
    @GetMapping("/")
    public RepresentationModel<?> readUserContacts() {
        return readUserContactsRequestHandler.handle();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsContactAuthorization.hasAuthorization(#contactId)")
    public EntityModel<ReadContactResponse> readContact(
            @PathVariable("id") Long contactId
    ) {
        return readContactRequestHandler.handle(contactId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@userOwnsCompanyAuthorization.hasAuthorization(#request.companyId)")
    public EntityModel<ReadContactResponse> createContact(
            @Valid @RequestBody CreateContactRequest request
    ) {
        return createContactRequestHandler.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsContactAuthorization.hasAuthorization(#contactId)")
    public EntityModel<ReadContactResponse> updateContact(
            @PathVariable("id") Long contactId,
            @Valid @RequestBody UpdateContactRequest request
    ) {
        return updateContactRequestHandler.handle(Pair.of(contactId, request));
    }

    @Override
    @GetMapping("/{id}/attachTo/{companyId}")
    @PreAuthorize("@userOwnsContactAuthorization.hasAuthorization(#contactId) && " +
            "@userOwnsCompanyAuthorization.hasAuthorization(#companyId)")
    public ResponseEntity<Void> attachContactToCompany(
            @PathVariable("id") Long contactId,
            @PathVariable("companyId") Long companyId
    ) {
        return attachContactToCompanyRequestHandler.handle(Pair.of(contactId, companyId));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsContactAuthorization.hasAuthorization(#contactId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteContact(
            @PathVariable("id") Long contactId
    ) {
        return deleteContactRequestHandler.handle(contactId);
    }
}
