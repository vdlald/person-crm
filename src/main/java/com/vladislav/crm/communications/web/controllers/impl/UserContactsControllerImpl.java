package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.web.adapters.contacts.*;
import com.vladislav.crm.communications.web.controllers.UserContactsController;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactsControllerImpl implements UserContactsController {

    private final ReadUserContactsRequestHandlerAdapter readUserContactsRequestHandlerAdapter;
    private final ReadContactRequestHandlerAdapter readContactRequestHandlerAdapter;
    private final CreateContactRequestHandlerAdapter createContactRequestHandlerAdapter;
    private final UpdateContactRequestHandlerAdapter updateContactRequestHandlerAdapter;
    private final AttachContactToCompanyRequestHandlerAdapter attachContactToCompanyRequestHandlerAdapter;
    private final DeleteContactRequestHandlerAdapter deleteContactRequestHandlerAdapter;

    @Override
    @GetMapping("/")
    public RepresentationModel<?> readUserContacts() {
        return readUserContactsRequestHandlerAdapter.handle();
    }

    @Override
    @GetMapping("/{id}")
    public EntityModel<ReadContactResponse> readContact(
            @PathVariable("id") Long contactId
    ) {
        return readContactRequestHandlerAdapter.handle(contactId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ReadContactResponse> createContact(
            @Valid @RequestBody CreateContactRequest request
    ) {
        return createContactRequestHandlerAdapter.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    public EntityModel<ReadContactResponse> updateContact(
            @PathVariable("id") Long contactId,
            @Valid @RequestBody UpdateContactRequest request
    ) {
        return updateContactRequestHandlerAdapter.handle(Pair.of(contactId, request));
    }

    @Override
    @GetMapping("/{id}/attachTo/{companyId}")
    public ResponseEntity<Void> attachContactToCompany(
            @PathVariable("id") Long contactId,
            @PathVariable("companyId") Long companyId
    ) {
        return attachContactToCompanyRequestHandlerAdapter.handle(Pair.of(contactId, companyId));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteContact(
            @PathVariable("id") Long contactId
    ) {
        return deleteContactRequestHandlerAdapter.handle(contactId);
    }
}
