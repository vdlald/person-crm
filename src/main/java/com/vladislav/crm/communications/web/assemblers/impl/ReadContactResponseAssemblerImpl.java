package com.vladislav.crm.communications.web.assemblers.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.communications.web.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserContactsControllerImpl;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReadContactResponseAssemblerImpl implements ReadContactResponseAssembler {
    @Override
    public EntityModel<ReadContactResponse> toModel(Contact entity) {
        final ReadContactResponse response = new ReadContactResponse()
                .setId(entity.getId())
                .setName(entity.getName())
                .setUserId(entity.getUser().getId());

        final Company company = entity.getCompany();
        if (company != null) {
            response.setCompany(new CompanyResponse()
                    .setName(company.getName())
                    .setId(company.getId()));
        }

        final Link selfRel = linkTo(methodOn(UserContactsControllerImpl.class)
                .readContact(entity.getId()))
                .withSelfRel();

        return EntityModel.of(response, selfRel);
    }
}
