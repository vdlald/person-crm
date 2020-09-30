package com.vladislav.crm.controllers.assemblers;

import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import com.vladislav.crm.entities.Pipeline;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadPipelineResponseAssembler extends RepresentationModelAssembler<Pipeline, EntityModel<ReadPipelineResponse>> {
}
