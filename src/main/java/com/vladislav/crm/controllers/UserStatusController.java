package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.requests.CreateStatusRequest;
import com.vladislav.crm.controllers.requests.UpdateStatusRequest;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface UserStatusController {
    EntityModel<ReadStatusResponse> readStatus(Long statusId);

    EntityModel<ReadStatusResponse> createStatus(CreateStatusRequest request);

    EntityModel<ReadStatusResponse> updatePipeline(Long statusId, UpdateStatusRequest request);

    ResponseEntity<Void> deleteStatus(Long statusId);
}
