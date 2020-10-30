package com.vladislav.crm.communications.web.handlers.statuses.impl;

import com.vladislav.crm.services.operations.leads.ReadStatusLeadsOperation;
import com.vladislav.crm.communications.web.assemblers.ReadLeadResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserStatusControllerImpl;
import com.vladislav.crm.communications.web.handlers.statuses.ReadStatusLeadsRequestHandler;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadStatusLeadsRequestHandlerImpl implements ReadStatusLeadsRequestHandler {

    private final ReadLeadResponseAssembler readLeadResponseAssembler;
    private final ReadStatusLeadsOperation readStatusLeadsOperation;

    @Override
    public RepresentationModel<?> handle(Long statusId) {
        final List<EntityModel<ReadLeadResponse>> leads = readStatusLeadsOperation.execute(statusId)
                .stream()
                .map(readLeadResponseAssembler::toModel)
                .collect(Collectors.toUnmodifiableList());

        return HalModelBuilder.emptyHalModel()
                .embed(leads, LinkRelation.of("leads"))
                .link(linkTo(methodOn(UserStatusControllerImpl.class).readStatusLeads(statusId)).withSelfRel())
                .build();
    }
}
