package com.vladislav.crm.web.assemblers.impl;

import com.vladislav.crm.web.assemblers.ReadStatusResponseAssembler;
import com.vladislav.crm.web.controllers.impl.UserStatusControllerImpl;
import com.vladislav.crm.web.responses.ReadStatusResponse;
import com.vladislav.crm.entities.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReadStatusResponseAssemblerImpl implements ReadStatusResponseAssembler {
    @Override
    public EntityModel<ReadStatusResponse> toModel(Status entity) {
        final ReadStatusResponse response = new ReadStatusResponse()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPipelineId(entity.getPipeline().getId());  // refactor candidate: возможно здесь лучше сделать отдельный запрос на получение id ?

        final Link selfRel = linkTo(methodOn(UserStatusControllerImpl.class)
                .readStatus(entity.getId()))
                .withSelfRel();

        return EntityModel.of(response, selfRel);
    }
}
