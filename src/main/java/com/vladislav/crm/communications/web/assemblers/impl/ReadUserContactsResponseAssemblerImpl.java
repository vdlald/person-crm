package com.vladislav.crm.communications.web.assemblers.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.communications.web.assemblers.ReadUserContactsResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserContactsControllerImpl;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import com.vladislav.crm.communications.web.responses.ReadUserContactsResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReadUserContactsResponseAssemblerImpl implements ReadUserContactsResponseAssembler {
    @Override
    public EntityModel<ReadUserContactsResponse> toModel(Contact contact) {
        final Link selfRel = linkTo(methodOn(UserContactsControllerImpl.class)
                .readContact(contact.getId()))
                .withSelfRel();

        final ReadUserContactsResponse response = new ReadUserContactsResponse()
                .setId(contact.getId())
                .setName(contact.getName());

        final Company company = contact.getCompany();
        if (company != null) {
            response.setCompany(
                    new CompanyResponse()
                            .setId(company.getId())
                            .setName(company.getName())
            );
        }

        return EntityModel.of(response, selfRel);
    }
}
