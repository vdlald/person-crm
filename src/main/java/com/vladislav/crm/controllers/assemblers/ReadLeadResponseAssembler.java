package com.vladislav.crm.controllers.assemblers;

import com.vladislav.crm.controllers.responses.ReadLeadResponse;
import com.vladislav.crm.entities.Lead;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadLeadResponseAssembler extends RepresentationModelAssembler<Lead, EntityModel<ReadLeadResponse>> {
}
