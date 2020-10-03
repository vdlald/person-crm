package com.vladislav.crm.controllers.impl;

import com.vladislav.crm.controllers.UserStatusController;
import com.vladislav.crm.controllers.requesthandlers.statuses.ReadStatusRequestHandler;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statuses")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserStatusControllerImpl implements UserStatusController {

    private final ReadStatusRequestHandler readStatusRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsStatusAuthorization.hasAuthorization(#statusId)")
    public EntityModel<ReadStatusResponse> readStatus(@PathVariable("id") Long statusId) {
        return readStatusRequestHandler.handle(statusId);
    }
}
