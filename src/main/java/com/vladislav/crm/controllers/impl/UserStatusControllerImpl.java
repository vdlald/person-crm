package com.vladislav.crm.controllers.impl;

import com.vladislav.crm.controllers.UserStatusController;
import com.vladislav.crm.controllers.requesthandlers.statuses.CreateStatusRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.statuses.DeleteStatusRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.statuses.ReadStatusRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.statuses.UpdateStatusRequestHandler;
import com.vladislav.crm.controllers.requests.CreateStatusRequest;
import com.vladislav.crm.controllers.requests.UpdateStatusRequest;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/statuses")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserStatusControllerImpl implements UserStatusController {

    private final ReadStatusRequestHandler readStatusRequestHandler;
    private final CreateStatusRequestHandler createStatusRequestHandler;
    private final UpdateStatusRequestHandler updateStatusRequestHandler;
    private final DeleteStatusRequestHandler deleteStatusRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    public EntityModel<ReadStatusResponse> readStatus(@PathVariable("id") Long statusId) {
        return readStatusRequestHandler.handle(statusId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ReadStatusResponse> createStatus(@RequestBody CreateStatusRequest request) {
        return createStatusRequestHandler.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    public EntityModel<ReadStatusResponse> updatePipeline(
            @PathVariable("id") Long statusId,
            @RequestBody UpdateStatusRequest request
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
}
