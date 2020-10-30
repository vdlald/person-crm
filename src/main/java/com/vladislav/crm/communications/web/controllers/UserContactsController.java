package com.vladislav.crm.communications.web.controllers;

import com.vladislav.crm.communications.web.requests.CreateContactRequest;
import com.vladislav.crm.communications.web.requests.UpdateContactRequest;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

public interface UserContactsController {
    RepresentationModel<?> readUserContacts();

    EntityModel<ReadContactResponse> readContact(Long contactId);

    EntityModel<ReadContactResponse> createContact(CreateContactRequest request);

    EntityModel<ReadContactResponse> updateContact(Long contactId, UpdateContactRequest request);

    ResponseEntity<Void> attachContactToCompany(Long contactId, Long companyId);

    ResponseEntity<Void> deleteContact(Long contactId);
}
