package com.vladislav.crm.communications.web.assemblers;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadPipelineResponseAssembler extends RepresentationModelAssembler<Pipeline, EntityModel<ReadPipelineResponse>> {
}
