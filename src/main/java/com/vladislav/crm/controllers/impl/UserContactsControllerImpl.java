package com.vladislav.crm.controllers.impl;

import com.vladislav.crm.controllers.UserContactsController;
import com.vladislav.crm.controllers.requesthandlers.*;
import com.vladislav.crm.controllers.requests.CreateContactRequest;
import com.vladislav.crm.controllers.requests.UpdateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactsControllerImpl implements UserContactsController {

    private final ReadUserContactsRequestHandler readUserContactsRequestHandler;
    private final ReadContactRequestHandler readContactRequestHandler;
    private final CreateContactRequestHandler createContactRequestHandler;
    private final UpdateContactRequestHandler updateContactRequestHandler;
    private final DeleteContactRequestHandler deleteContactRequestHandler;

    @Override
    @GetMapping("/")
    public RepresentationModel<?> readUserContacts() {
        return readUserContactsRequestHandler.handle();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsContactAuthorization.hasAuthorization(#contactId)")
    public ResponseEntity<EntityModel<ReadContactResponse>> readContact(
            @PathVariable("id") Long contactId
    ) {
        return readContactRequestHandler.handle(contactId);
    }

    @Override
    @PostMapping("/")
    public EntityModel<ReadContactResponse> createContact(
            @RequestBody CreateContactRequest request
    ) {
        return createContactRequestHandler.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsContactAuthorization.hasAuthorization(#contactId)")
    public ResponseEntity<EntityModel<ReadContactResponse>> updateContact(
            @PathVariable("id") Long contactId,
            @RequestBody UpdateContactRequest request
    ) {
        return updateContactRequestHandler.handle(Pair.of(contactId, request));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsContactAuthorization.hasAuthorization(#contactId)")
    public ResponseEntity<Void> deleteContact(
            @PathVariable("id") Long contactId
    ) {
        return deleteContactRequestHandler.handle(contactId);
    }
}
