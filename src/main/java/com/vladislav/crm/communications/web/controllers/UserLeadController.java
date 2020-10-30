package com.vladislav.crm.communications.web.controllers;

import com.vladislav.crm.communications.web.requests.CreateLeadRequest;
import com.vladislav.crm.communications.web.requests.UpdateLeadRequest;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserLeadController {
    EntityModel<ReadLeadResponse> readLead(Long leadId);

    EntityModel<ReadLeadResponse> createLead(CreateLeadRequest request);

    EntityModel<ReadLeadResponse> updateContact(Long leadId, UpdateLeadRequest request);

    ResponseEntity<Void> deleteLead(Long leadId);

    ResponseEntity<Void> moveLeadToAnotherStatus(Long leadId, Long statusId);

    ResponseEntity<Void> attachLeadToContact(Long leadId, Long contactId);

    void getAllLeadsInExcel(HttpServletRequest request, HttpServletResponse response);
}
