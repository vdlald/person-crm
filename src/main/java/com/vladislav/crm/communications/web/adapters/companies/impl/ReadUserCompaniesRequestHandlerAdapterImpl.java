package com.vladislav.crm.communications.web.adapters.companies.impl;

import com.vladislav.crm.communications.handlers.companies.ReadUserCompaniesRequestHandler;
import com.vladislav.crm.communications.web.adapters.companies.ReadUserCompaniesRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.CompanyResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserCompaniesControllerImpl;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
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
public class ReadUserCompaniesRequestHandlerAdapterImpl implements ReadUserCompaniesRequestHandlerAdapter {

    private final ReadUserCompaniesRequestHandler requestHandler;
    private final CompanyResponseAssembler companyResponseAssembler;

    @Override
    public RepresentationModel<?> handle(Void unused) {
        final List<EntityModel<CompanyResponse>> companies = requestHandler.handle()
                .stream()
                .map(companyResponseAssembler::toModel)
                .collect(Collectors.toUnmodifiableList());

        return HalModelBuilder.emptyHalModel()
                .embed(companies, LinkRelation.of("companies"))
                .link(linkTo(methodOn(UserCompaniesControllerImpl.class).readUserCompanies()).withSelfRel())
                .build();
    }

    @Override
    public RepresentationModel<?> handle() {
        return handle(null);
    }
}
