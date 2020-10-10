package com.vladislav.crm.web.controllers.impl;

import com.vladislav.crm.web.controllers.UserStatusController;
import com.vladislav.crm.web.handlers.statuses.*;
import com.vladislav.crm.web.requests.CreateStatusRequest;
import com.vladislav.crm.web.requests.UpdateStatusRequest;
import com.vladislav.crm.web.responses.ReadStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/statuses")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserStatusControllerImpl implements UserStatusController {

    private final ReadStatusRequestHandler readStatusRequestHandler;
    private final CreateStatusRequestHandler createStatusRequestHandler;
    private final UpdateStatusRequestHandler updateStatusRequestHandler;
    private final DeleteStatusRequestHandler deleteStatusRequestHandler;
    private final ReadStatusLeadsRequestHandler readStatusLeadsRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    public EntityModel<ReadStatusResponse> readStatus(@PathVariable("id") Long statusId) {
        return readStatusRequestHandler.handle(statusId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ReadStatusResponse> createStatus(@Valid @RequestBody CreateStatusRequest request) {
        return createStatusRequestHandler.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    public EntityModel<ReadStatusResponse> updatePipeline(
            @PathVariable("id") Long statusId,
            @Valid @RequestBody UpdateStatusRequest request
    ) {
        return updateStatusRequestHandler.handle(Pair.of(statusId, request));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteStatus(@PathVariable("id") Long statusId) {
        return deleteStatusRequestHandler.handle(statusId);
    }

    @Override
    @GetMapping("/{id}/leads")
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    public RepresentationModel<?> readStatusLeads(@PathVariable("id") Long statusId) {
        return readStatusLeadsRequestHandler.handle(statusId);
    }
}
