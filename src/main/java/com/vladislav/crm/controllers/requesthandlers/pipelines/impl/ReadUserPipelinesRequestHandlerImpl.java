package com.vladislav.crm.controllers.requesthandlers.pipelines.impl;

import com.vladislav.crm.controllers.assemblers.ReadUserPipelinesResponseAssembler;
import com.vladislav.crm.controllers.impl.UserPipelinesControllerImpl;
import com.vladislav.crm.controllers.requesthandlers.pipelines.ReadUserPipelinesRequestHandler;
import com.vladislav.crm.controllers.responses.ReadUserPipelinesResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.pipelines.ReadUserPipelinesOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
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

    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
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
