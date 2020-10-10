package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.CreatePipelineRequest;
import com.vladislav.crm.web.requests.UpdatePipelineRequest;
import com.vladislav.crm.web.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

public interface UserPipelinesController {
    RepresentationModel<?> readUserPipelines();

    EntityModel<ReadPipelineResponse> readPipeline(Long pipelineId);

    EntityModel<ReadPipelineResponse> createPipeline(CreatePipelineRequest request);

    EntityModel<ReadPipelineResponse> updatePipeline(Long pipelineId, UpdatePipelineRequest request);

    ResponseEntity<Void> deletePipeline(Long pipelineId);

    RepresentationModel<?> readPipelineStatuses(Long pipelineId);
}
