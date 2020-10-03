package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.CreatePipelineRequest;
import com.vladislav.crm.web.requests.UpdatePipelineRequest;
import com.vladislav.crm.web.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deletePipeline(
            @PathVariable("id") Long pipelineId
    );
}