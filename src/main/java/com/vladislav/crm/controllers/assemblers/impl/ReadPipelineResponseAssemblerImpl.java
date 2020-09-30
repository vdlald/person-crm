package com.vladislav.crm.controllers.assemblers.impl;

import com.vladislav.crm.controllers.assemblers.ReadPipelineResponseAssembler;
import com.vladislav.crm.controllers.impl.UserPipelinesControllerImpl;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.entities.Pipeline;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReadPipelineResponseAssemblerImpl implements ReadPipelineResponseAssembler {

    @Override
    public EntityModel<ReadPipelineResponse> toModel(Pipeline entity) {
        final List<Long> statuses = entity.getStatuses().stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toUnmodifiableList());

        final ReadPipelineResponse response = new ReadPipelineResponse()
                .setId(entity.getId())
                .setName(entity.getName())
                .setUserId(entity.getUser().getId())
                .setStatusesId(statuses);

        final Link selfRel = linkTo(methodOn(UserPipelinesControllerImpl.class)
                .readPipeline(entity.getId()))
                .withSelfRel();

        return EntityModel.of(response, selfRel);
    }
}
