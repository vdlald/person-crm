package com.vladislav.crm.communications.web.assemblers;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.communications.web.responses.ReadUserPipelinesResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadUserPipelinesResponseAssembler extends RepresentationModelAssembler<Pipeline, EntityModel<ReadUserPipelinesResponse>> {
}
