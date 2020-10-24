package com.vladislav.crm.web.assemblers;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.web.responses.ReadUserContactsResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadUserContactsResponseAssembler
        extends RepresentationModelAssembler<Contact, EntityModel<ReadUserContactsResponse>> {
}
