package com.vladislav.crm.controllers.impl;

import com.vladislav.crm.controllers.UserPipelinesController;
import com.vladislav.crm.controllers.requesthandlers.pipelines.ReadPipelineRequestHandler;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pipelines")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserPipelinesControllerImpl implements UserPipelinesController {

    private final ReadPipelineRequestHandler readPipelineRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    public EntityModel<ReadPipelineResponse> readPipeline(@PathVariable("id") Long pipelineId) {
        return readPipelineRequestHandler.handle(pipelineId);
    }
}
