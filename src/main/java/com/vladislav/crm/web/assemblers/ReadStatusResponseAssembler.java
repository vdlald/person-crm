package com.vladislav.crm.web.assemblers;

import com.vladislav.crm.web.responses.ReadStatusResponse;
import com.vladislav.crm.entities.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadStatusResponseAssembler extends RepresentationModelAssembler<Status, EntityModel<ReadStatusResponse>> {
}
