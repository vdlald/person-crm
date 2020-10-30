package com.vladislav.crm.communications.web.handlers.pipelines.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.pipelines.ReadUserPipelinesOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.communications.web.assemblers.ReadUserPipelinesResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserPipelinesControllerImpl;
import com.vladislav.crm.communications.web.handlers.pipelines.ReadUserPipelinesRequestHandler;
import com.vladislav.crm.communications.web.responses.ReadUserPipelinesResponse;
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
public class ReadUserPipelinesRequestHandlerImpl implements ReadUserPipelinesRequestHandler {

    private final GetCurrentUserOperation getCurrentUserStubOperation;
    private final ReadUserPipelinesResponseAssembler readUserPipelinesResponseAssembler;
    private final ReadUserPipelinesOperation readUserPipelinesOperation;

    @Override
    public RepresentationModel<?> handle(Void unused) {
        final User user = getCurrentUserStubOperation.execute();

        final List<EntityModel<ReadUserPipelinesResponse>> models = readUserPipelinesOperation.execute(user.getId())
                .stream()
                .map(readUserPipelinesResponseAssembler::toModel)
                .collect(Collectors.toUnmodifiableList());

        return HalModelBuilder.emptyHalModel()
                .embed(models, LinkRelation.of("pipelines"))
                .link(linkTo(methodOn(UserPipelinesControllerImpl.class).readUserPipelines()).withSelfRel())
                .build();
    }

    @Override
    public RepresentationModel<?> handle() {
        return handle(null);
    }
}
