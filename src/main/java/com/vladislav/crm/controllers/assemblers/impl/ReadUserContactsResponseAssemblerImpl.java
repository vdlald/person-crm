package com.vladislav.crm.controllers.assemblers.impl;

import com.vladislav.crm.controllers.assemblers.ReadUserContactsResponseAssembler;
import com.vladislav.crm.controllers.impl.UserContactsControllerImpl;
import com.vladislav.crm.controllers.responses.CompanyResponse;
import com.vladislav.crm.controllers.responses.ReadUserContactsResponse;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
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
            response.setCompany(new CompanyResponse().setName(company.getName()));
        }

        return EntityModel.of(response, selfRel);
    }
}
