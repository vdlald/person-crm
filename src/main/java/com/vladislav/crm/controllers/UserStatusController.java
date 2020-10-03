package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.requests.CreateStatusRequest;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface UserStatusController {
    EntityModel<ReadStatusResponse> readStatus(Long statusId);

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<ReadStatusResponse> createStatus(@RequestBody CreateStatusRequest request);

    ResponseEntity<Void> deleteStatus(Long statusId);
}
