package com.vladislav.crm.web.assemblers;

import com.vladislav.crm.web.responses.ReadPipelineResponse;
import com.vladislav.crm.entities.Pipeline;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadPipelineResponseAssembler extends RepresentationModelAssembler<Pipeline, EntityModel<ReadPipelineResponse>> {
}
