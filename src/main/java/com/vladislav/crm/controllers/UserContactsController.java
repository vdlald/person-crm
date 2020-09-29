package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.requests.CreateContactRequest;
import com.vladislav.crm.controllers.requests.UpdateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

public interface UserContactsController {
    RepresentationModel<?> readUserContacts();

    ResponseEntity<EntityModel<ReadContactResponse>> readContact(Long contactId);

    EntityModel<ReadContactResponse> createContact(CreateContactRequest request);

    ResponseEntity<EntityModel<ReadContactResponse>> updateContact(Long contactId, UpdateContactRequest request);

    ResponseEntity<Void> deleteContact(Long contactId);
}
