package com.vladislav.crm.controllers.assemblers.impl;

import com.vladislav.crm.controllers.assemblers.ReadStatusResponseAssembler;
import com.vladislav.crm.controllers.impl.UserStatusControllerImpl;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
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
        final ReadStatusResponse response = new ReadStatusResponse().setName(entity.getName());

        final Link selfRel = linkTo(methodOn(UserStatusControllerImpl.class)
                .readStatus(entity.getId()))
                .withSelfRel();

        return EntityModel.of(response, selfRel);
    }
}
