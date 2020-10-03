package com.vladislav.crm.controllers.impl;

import com.vladislav.crm.controllers.UserStatusController;
import com.vladislav.crm.controllers.requesthandlers.statuses.ReadStatusRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.statuses.impl.DeleteStatusRequestHandler;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final DeleteStatusRequestHandler deleteStatusRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    public EntityModel<ReadStatusResponse> readStatus(@PathVariable("id") Long statusId) {
        return readStatusRequestHandler.handle(statusId);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteStatus(@PathVariable("id") Long statusId) {
        return deleteStatusRequestHandler.handle(statusId);
    }
}
