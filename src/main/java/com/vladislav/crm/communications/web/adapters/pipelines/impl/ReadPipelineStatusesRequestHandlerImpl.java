package com.vladislav.crm.communications.web.adapters.pipelines.impl;

import com.vladislav.crm.services.operations.statuses.ReadPipelineStatusesOperation;
import com.vladislav.crm.communications.web.assemblers.ReadStatusResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserPipelinesControllerImpl;
import com.vladislav.crm.communications.web.adapters.pipelines.ReadPipelineStatusesRequestHandler;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
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
public class ReadPipelineStatusesRequestHandlerImpl implements ReadPipelineStatusesRequestHandler {

    private final ReadStatusResponseAssembler readStatusResponseAssembler;
    private final ReadPipelineStatusesOperation readPipelineStatusesOperation;

    @Override
    public RepresentationModel<?> handle(Long pipelineId) {
        final List<EntityModel<ReadStatusResponse>> statuses = readPipelineStatusesOperation.execute(pipelineId)
                .stream()
                .map(readStatusResponseAssembler::toModel)
                .collect(Collectors.toUnmodifiableList());

        return HalModelBuilder.emptyHalModel()
                .embed(statuses, LinkRelation.of("statuses"))
                .link(linkTo(methodOn(UserPipelinesControllerImpl.class).readPipelineStatuses(pipelineId)).withSelfRel())
                .build();
    }
}
