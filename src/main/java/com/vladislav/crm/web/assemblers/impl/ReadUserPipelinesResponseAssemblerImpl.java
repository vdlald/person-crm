package com.vladislav.crm.web.assemblers.impl;

import com.vladislav.crm.web.assemblers.ReadUserPipelinesResponseAssembler;
import com.vladislav.crm.web.controllers.impl.UserPipelinesControllerImpl;
import com.vladislav.crm.web.responses.ReadUserPipelinesResponse;
import com.vladislav.crm.entities.Pipeline;
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
