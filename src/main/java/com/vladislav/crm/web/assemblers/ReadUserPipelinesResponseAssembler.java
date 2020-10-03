package com.vladislav.crm.web.assemblers;

import com.vladislav.crm.web.responses.ReadUserPipelinesResponse;
import com.vladislav.crm.entities.Pipeline;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadUserPipelinesResponseAssembler extends RepresentationModelAssembler<Pipeline, EntityModel<ReadUserPipelinesResponse>> {
}
