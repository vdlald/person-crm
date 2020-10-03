package com.vladislav.crm.web.controllers.impl;

import com.vladislav.crm.web.controllers.UserPipelinesController;
import com.vladislav.crm.web.handlers.pipelines.*;
import com.vladislav.crm.web.requests.CreatePipelineRequest;
import com.vladislav.crm.web.requests.UpdatePipelineRequest;
import com.vladislav.crm.web.responses.ReadPipelineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pipelines")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserPipelinesControllerImpl implements UserPipelinesController {

    private final ReadPipelineRequestHandler readPipelineRequestHandler;
    private final ReadUserPipelinesRequestHandler readUserPipelinesRequestHandler;
    private final CreatePipelineRequestHandler createPipelineRequestHandler;
    private final UpdatePipelineRequestHandler updatePipelineRequestHandler;
    private final DeletePipelineRequestHandler deletePipelineRequestHandler;

    @Override
    @GetMapping("/")
    public RepresentationModel<?> readUserPipelines() {
        return readUserPipelinesRequestHandler.handle();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    public EntityModel<ReadPipelineResponse> readPipeline(@PathVariable("id") Long pipelineId) {
        return readPipelineRequestHandler.handle(pipelineId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ReadPipelineResponse> createPipeline(@RequestBody CreatePipelineRequest request) {
        return createPipelineRequestHandler.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    public EntityModel<ReadPipelineResponse> updatePipeline(
            @PathVariable("id") Long pipelineId,
            @RequestBody UpdatePipelineRequest request
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
}
