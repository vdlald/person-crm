package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface UserStatusController {
    EntityModel<ReadStatusResponse> readStatus(Long statusId);

    ResponseEntity<Void> deleteStatus(Long statusId);
}
