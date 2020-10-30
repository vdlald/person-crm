package com.vladislav.crm.communications.web.assemblers;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadContactResponseAssembler
        extends RepresentationModelAssembler<Contact, EntityModel<ReadContactResponse>> {
}
