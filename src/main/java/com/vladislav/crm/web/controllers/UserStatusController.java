package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.CreateStatusRequest;
import com.vladislav.crm.web.requests.UpdateStatusRequest;
import com.vladislav.crm.web.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

public interface UserStatusController {
    EntityModel<ReadStatusResponse> readStatus(Long statusId);

    EntityModel<ReadStatusResponse> createStatus(CreateStatusRequest request);

    EntityModel<ReadStatusResponse> updatePipeline(Long statusId, UpdateStatusRequest request);

    ResponseEntity<Void> deleteStatus(Long statusId);

    RepresentationModel<?> readStatusLeads(Long statusId);
}
