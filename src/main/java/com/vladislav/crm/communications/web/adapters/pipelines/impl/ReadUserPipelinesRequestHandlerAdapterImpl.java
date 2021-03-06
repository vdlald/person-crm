package com.vladislav.crm.communications.web.adapters.pipelines.impl;

import com.vladislav.crm.communications.handlers.pipelines.ReadUserPipelinesRequestHandler;
import com.vladislav.crm.communications.web.adapters.pipelines.ReadUserPipelinesRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadUserPipelinesResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserPipelinesControllerImpl;
import com.vladislav.crm.communications.web.responses.ReadUserPipelinesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component("webReadUserPipelinesRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserPipelinesRequestHandlerAdapterImpl implements ReadUserPipelinesRequestHandlerAdapter {

    private final ReadUserPipelinesResponseAssembler readUserPipelinesResponseAssembler;
    private final ReadUserPipelinesRequestHandler requestHandler;

    @Override
    public RepresentationModel<?> handle(Void unused) {
        final List<EntityModel<ReadUserPipelinesResponse>> models = requestHandler.handle()
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
