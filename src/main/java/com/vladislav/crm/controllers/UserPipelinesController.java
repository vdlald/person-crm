package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserPipelinesController {
    @GetMapping("/")
    RepresentationModel<?> readUserPipelines();

    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    EntityModel<ReadPipelineResponse> readPipeline(@PathVariable("id") Long pipelineId);
}
