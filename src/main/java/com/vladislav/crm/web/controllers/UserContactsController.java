package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.CreateContactRequest;
import com.vladislav.crm.web.requests.UpdateContactRequest;
import com.vladislav.crm.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

public interface UserContactsController {
    RepresentationModel<?> readUserContacts();

    EntityModel<ReadContactResponse> readContact(Long contactId);

    EntityModel<ReadContactResponse> createContact(CreateContactRequest request);

    EntityModel<ReadContactResponse> updateContact(Long contactId, UpdateContactRequest request);

    ResponseEntity<Void> deleteContact(Long contactId);
}
