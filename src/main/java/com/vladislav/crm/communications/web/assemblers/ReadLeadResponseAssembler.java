package com.vladislav.crm.communications.web.assemblers;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadLeadResponseAssembler extends RepresentationModelAssembler<Lead, EntityModel<ReadLeadResponse>> {
}
