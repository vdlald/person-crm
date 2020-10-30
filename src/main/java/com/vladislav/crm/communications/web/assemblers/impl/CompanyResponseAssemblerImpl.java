package com.vladislav.crm.communications.web.assemblers.impl;

import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.communications.web.assemblers.CompanyResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserCompaniesControllerImpl;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CompanyResponseAssemblerImpl implements CompanyResponseAssembler {
    @Override
    public EntityModel<CompanyResponse> toModel(Company entity) {
        final CompanyResponse response = new CompanyResponse()
                .setId(entity.getId())
                .setName(entity.getName())
                .setContactsId(entity.getContacts().stream()
                        .map(AbstractEntity::getId)
                        .collect(Collectors.toUnmodifiableList()));

        final Link selfRel = linkTo(methodOn(UserCompaniesControllerImpl.class)
                .readCompany(entity.getId()))
                .withSelfRel();

        return EntityModel.of(response, selfRel);
    }
}
