package com.vladislav.crm.web.assemblers;

import com.vladislav.crm.web.responses.ReadLeadResponse;
import com.vladislav.crm.entities.Lead;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadLeadResponseAssembler extends RepresentationModelAssembler<Lead, EntityModel<ReadLeadResponse>> {
}
