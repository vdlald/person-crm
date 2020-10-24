package com.vladislav.crm.web.handlers.contacts.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.contacts.ReadUserContactsOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserStubOperation;
import com.vladislav.crm.web.assemblers.ReadUserContactsResponseAssembler;
import com.vladislav.crm.web.controllers.impl.UserContactsControllerImpl;
import com.vladislav.crm.web.handlers.contacts.ReadUserContactsRequestHandler;
import com.vladislav.crm.web.responses.ReadUserContactsResponse;
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
public class ReadUserContactsRequestHandlerImpl implements ReadUserContactsRequestHandler {

    private final GetCurrentUserStubOperation getCurrentUserStubOperation;
    private final ReadUserContactsOperation readUserContactsOperation;
    private final ReadUserContactsResponseAssembler readUserContactsResponseAssembler;

    @Override
    public RepresentationModel<?> handle(Void unused) {
        final User user = getCurrentUserStubOperation.execute();

        final List<EntityModel<ReadUserContactsResponse>> models = readUserContactsOperation.execute(user.getId())
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
