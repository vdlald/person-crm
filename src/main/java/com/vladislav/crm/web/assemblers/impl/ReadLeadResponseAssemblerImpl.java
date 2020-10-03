package com.vladislav.crm.web.assemblers.impl;

import com.vladislav.crm.web.assemblers.ReadLeadResponseAssembler;
import com.vladislav.crm.web.controllers.impl.UserLeadControllerImpl;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.entities.Lead;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReadLeadResponseAssemblerImpl implements ReadLeadResponseAssembler {

    @Override
    public EntityModel<ReadLeadResponse> toModel(Lead entity) {
        final List<Long> contactsId = entity.getContacts()
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toUnmodifiableList());

        final ReadLeadResponse response = new ReadLeadResponse()
                .setId(entity.getId())
                .setName(entity.getName())
                .setSale(entity.getSale())
                .setUserId(entity.getUser().getId())
                .setStatusId(entity.getStatus().getId())
                .setContactsId(contactsId)
                .setUpdatedAt(entity.getUpdatedAt())
                .setCreatedAt(entity.getCreatedAt());

        final Link selfRel = linkTo(methodOn(UserLeadControllerImpl.class)
                .readLead(entity.getId()))
                .withSelfRel();

        return EntityModel.of(response, selfRel);
    }
}
