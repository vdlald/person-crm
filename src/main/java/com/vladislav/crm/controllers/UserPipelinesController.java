package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.requests.CreatePipelineRequest;
import com.vladislav.crm.controllers.requests.UpdatePipelineRequest;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

public interface UserPipelinesController {
    @GetMapping("/")
    RepresentationModel<?> readUserPipelines();

    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    EntityModel<ReadPipelineResponse> readPipeline(@PathVariable("id") Long pipelineId);

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<ReadPipelineResponse> createPipeline(@RequestBody CreatePipelineRequest request);

    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    EntityModel<ReadPipelineResponse> updatePipeline(
            @PathVariable("id") Long pipelineId,
            @RequestBody UpdatePipelineRequest request
    );
}
