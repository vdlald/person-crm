package com.vladislav.crm.controllers.assemblers;

import com.vladislav.crm.controllers.UserContactsController;
import com.vladislav.crm.controllers.responses.ReadUserContactsResponse;
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
        final Link selfRel = linkTo(methodOn(UserContactsController.class)
                .readContact(contact.getId()))
                .withSelfRel();

        final ReadUserContactsResponse response = new ReadUserContactsResponse()
                .setContactId(contact.getId())
                .setName(contact.getName())
                .setCompany(contact.getCompany());

        return EntityModel.of(response, selfRel);
    }
}
