package com.vladislav.crm.controllers.assemblers;

import com.vladislav.crm.controllers.responses.ReadUserPipelinesResponse;
import com.vladislav.crm.entities.Pipeline;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadUserPipelinesResponseAssembler extends RepresentationModelAssembler<Pipeline, EntityModel<ReadUserPipelinesResponse>> {
}
