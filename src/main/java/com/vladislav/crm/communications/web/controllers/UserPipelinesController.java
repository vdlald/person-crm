package com.vladislav.crm.communications.web.controllers;

import com.vladislav.crm.communications.web.requests.CreatePipelineRequest;
import com.vladislav.crm.communications.web.requests.UpdatePipelineRequest;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
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
