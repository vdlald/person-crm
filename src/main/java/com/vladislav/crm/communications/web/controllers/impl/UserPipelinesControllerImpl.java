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

    private final ReadPipelineRequestHandlerAdapter readPipelineRequestHandlerAdapter;
    private final ReadUserPipelinesRequestHandlerAdapter readUserPipelinesRequestHandlerAdapter;
    private final CreatePipelineRequestHandlerAdapter createPipelineRequestHandlerAdapter;
    private final UpdatePipelineRequestHandlerAdapter updatePipelineRequestHandlerAdapter;
    private final DeletePipelineRequestHandlerAdapter deletePipelineRequestHandlerAdapter;
    private final ReadPipelineStatusesRequestHandlerAdapter readPipelineStatusesRequestHandlerAdapter;

    @Override
    @GetMapping("/")
    public RepresentationModel<?> readUserPipelines() {
        return readUserPipelinesRequestHandlerAdapter.handle();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId) || " +
            "@userOwnsReadAllAuthorization.hasAuthorization()")
    public EntityModel<ReadPipelineResponse> readPipeline(
            @PathVariable("id") Long pipelineId
    ) {
        return readPipelineRequestHandlerAdapter.handle(pipelineId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ReadPipelineResponse> createPipeline(
            @Valid @RequestBody CreatePipelineRequest request
    ) {
        return createPipelineRequestHandlerAdapter.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    public EntityModel<ReadPipelineResponse> updatePipeline(
            @PathVariable("id") Long pipelineId,
            @Valid @RequestBody UpdatePipelineRequest request
    ) {
        return updatePipelineRequestHandlerAdapter.handle(Pair.of(pipelineId, request));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePipeline(
            @PathVariable("id") Long pipelineId
    ) {
        return deletePipelineRequestHandlerAdapter.handle(pipelineId);
    }

    @Override
    @GetMapping("/{id}/statuses")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId) || " +
            "@userOwnsReadAllAuthorization.hasAuthorization()")
    public RepresentationModel<?> readPipelineStatuses(
            @PathVariable("id") Long pipelineId
    ) {
        return readPipelineStatusesRequestHandlerAdapter.handle(pipelineId);
    }
}
