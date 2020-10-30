package com.vladislav.crm.communications.web.assemblers.impl;

import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.communications.web.assemblers.ReadUserPipelinesResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserPipelinesControllerImpl;
import com.vladislav.crm.communications.web.responses.ReadUserPipelinesResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReadUserPipelinesResponseAssemblerImpl implements ReadUserPipelinesResponseAssembler {
    @Override
    public EntityModel<ReadUserPipelinesResponse> toModel(Pipeline entity) {
        final Link selfRel = linkTo(methodOn(UserPipelinesControllerImpl.class)
                .readPipeline(entity.getId()))
                .withSelfRel();

        final ReadUserPipelinesResponse response = new ReadUserPipelinesResponse()
                .setId(entity.getId())
                .setName(entity.getName());

        return EntityModel.of(response, selfRel);
    }
}
