package com.vladislav.crm.controllers.assemblers;

import com.vladislav.crm.controllers.responses.ReadUserContactsResponse;
import com.vladislav.crm.entities.Contact;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadUserContactsResponseAssembler
        extends RepresentationModelAssembler<Contact, EntityModel<ReadUserContactsResponse>> {
}
