package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.web.adapters.statuses.*;
import com.vladislav.crm.communications.web.controllers.UserStatusController;
import com.vladislav.crm.communications.web.requests.CreateStatusRequest;
import com.vladislav.crm.communications.web.requests.UpdateStatusRequest;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/statuses")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserStatusControllerImpl implements UserStatusController {

    private final ReadStatusRequestHandlerAdapter readStatusRequestHandlerAdapter;
    private final CreateStatusRequestHandlerAdapter createStatusRequestHandlerAdapter;
    private final UpdateStatusRequestHandlerAdapter updateStatusRequestHandlerAdapter;
    private final DeleteStatusRequestHandlerAdapter deleteStatusRequestHandlerAdapter;
    private final ReadStatusLeadsRequestHandlerAdapter readStatusLeadsRequestHandlerAdapter;

    @Override
    @GetMapping("/{id}")
    public EntityModel<ReadStatusResponse> readStatus(@PathVariable("id") Long statusId) {
        return readStatusRequestHandlerAdapter.handle(statusId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ReadStatusResponse> createStatus(@Valid @RequestBody CreateStatusRequest request) {
        return createStatusRequestHandlerAdapter.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    public EntityModel<ReadStatusResponse> updatePipeline(
            @PathVariable("id") Long statusId,
            @Valid @RequestBody UpdateStatusRequest request
    ) {
        return updateStatusRequestHandlerAdapter.handle(Pair.of(statusId, request));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteStatus(@PathVariable("id") Long statusId) {
        return deleteStatusRequestHandlerAdapter.handle(statusId);
    }

    @Override
    @GetMapping("/{id}/leads")
    public RepresentationModel<?> readStatusLeads(@PathVariable("id") Long statusId) {
        return readStatusLeadsRequestHandlerAdapter.handle(statusId);
    }
}
