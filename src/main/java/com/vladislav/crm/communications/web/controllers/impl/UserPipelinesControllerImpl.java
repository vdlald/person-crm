package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.web.controllers.UserPipelinesController;
import com.vladislav.crm.communications.web.adapters.pipelines.*;
import com.vladislav.crm.communications.web.requests.CreatePipelineRequest;
import com.vladislav.crm.communications.web.requests.UpdatePipelineRequest;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
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
@RequestMapping("/api/v1/pipelines")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserPipelinesControllerImpl implements UserPipelinesController {

    private final ReadPipelineRequestHandler readPipelineRequestHandler;
    private final ReadUserPipelinesRequestHandler readUserPipelinesRequestHandler;
    private final CreatePipelineRequestHandler createPipelineRequestHandler;
    private final UpdatePipelineRequestHandler updatePipelineRequestHandler;
    private final DeletePipelineRequestHandler deletePipelineRequestHandler;
    private final ReadPipelineStatusesRequestHandler readPipelineStatusesRequestHandler;

    @Override
    @GetMapping("/")
    public RepresentationModel<?> readUserPipelines() {
        return readUserPipelinesRequestHandler.handle();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    public EntityModel<ReadPipelineResponse> readPipeline(
            @PathVariable("id") Long pipelineId
    ) {
        return readPipelineRequestHandler.handle(pipelineId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ReadPipelineResponse> createPipeline(
            @Valid @RequestBody CreatePipelineRequest request
    ) {
        return createPipelineRequestHandler.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    public EntityModel<ReadPipelineResponse> updatePipeline(
            @PathVariable("id") Long pipelineId,
            @Valid @RequestBody UpdatePipelineRequest request
    ) {
        return updatePipelineRequestHandler.handle(Pair.of(pipelineId, request));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePipeline(
            @PathVariable("id") Long pipelineId
    ) {
        return deletePipelineRequestHandler.handle(pipelineId);
    }

    @Override
    @GetMapping("/{id}/statuses")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    public RepresentationModel<?> readPipelineStatuses(
            @PathVariable("id") Long pipelineId
    ) {
        return readPipelineStatusesRequestHandler.handle(pipelineId);
    }
}
