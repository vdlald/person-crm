package com.vladislav.crm.controllers;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactsController {

    private final ReadUserContactsRequestHandler readUserContactsRequestHandler;
    private final ReadContactRequestHandler readContactRequestHandler;
    private final CreateContactRequestHandler createContactRequestHandler;
    private final UpdateContactRequestHandler updateContactRequestHandler;
    private final DeleteContactRequestHandler deleteContactRequestHandler;

    @GetMapping("/")
    public RepresentationModel<?> readUserContacts() {
        return readUserContactsRequestHandler.handle();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ReadContactResponse>> readContact(
            @PathVariable("id") Long contactId
    ) {
        return readContactRequestHandler.handle(contactId);
    }

    @PostMapping("/")
    public EntityModel<ReadContactResponse> createContact(
            @RequestBody CreateContactRequest request
    ) {
        return createContactRequestHandler.handle(request);
    }

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<ReadContactResponse>> updateContact(
            @PathVariable("id") Long contactId,
            @RequestBody UpdateContactRequest request
    ) {
        return updateContactRequestHandler.handle(Pair.of(contactId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(
            @PathVariable("id") Long contactId
    ) {
        return deleteContactRequestHandler.handle(contactId);
    }
}
