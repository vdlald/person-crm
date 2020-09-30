package com.vladislav.crm.controllers.impl;

import com.vladislav.crm.controllers.UserPipelinesController;
import com.vladislav.crm.controllers.requesthandlers.pipelines.CreatePipelineRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.pipelines.ReadPipelineRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.pipelines.ReadUserPipelinesRequestHandler;
import com.vladislav.crm.controllers.requests.CreatePipelineRequest;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pipelines")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserPipelinesControllerImpl implements UserPipelinesController {

    private final ReadPipelineRequestHandler readPipelineRequestHandler;
    private final ReadUserPipelinesRequestHandler readUserPipelinesRequestHandler;
    private final CreatePipelineRequestHandler createPipelineRequestHandler;

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
}
