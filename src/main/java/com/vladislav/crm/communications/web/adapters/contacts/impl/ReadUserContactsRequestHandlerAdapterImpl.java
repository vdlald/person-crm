package com.vladislav.crm.communications.web.adapters.contacts.impl;

import com.vladislav.crm.communications.handlers.contacts.ReadUserContactsRequestHandler;
import com.vladislav.crm.communications.web.adapters.contacts.ReadUserContactsRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadUserContactsResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserContactsControllerImpl;
import com.vladislav.crm.communications.web.responses.ReadUserContactsResponse;
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

@Component("webReadUserContactsRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserContactsRequestHandlerAdapterImpl implements ReadUserContactsRequestHandlerAdapter {

    private final ReadUserContactsRequestHandler requestHandler;
    private final ReadUserContactsResponseAssembler readUserContactsResponseAssembler;

    @Override
    public RepresentationModel<?> handle(Void unused) {
        final List<EntityModel<ReadUserContactsResponse>> models = requestHandler.handle()
                .stream()
                .map(readUserContactsResponseAssembler::toModel)
                .collect(Collectors.toUnmodifiableList());

        return HalModelBuilder.emptyHalModel()
                .embed(models, LinkRelation.of("contacts"))
                .link(linkTo(methodOn(UserContactsControllerImpl.class).readUserContacts()).withSelfRel())
                .build();
    }

    @Override
    public RepresentationModel<?> handle() {
        return handle(null);
    }
}
