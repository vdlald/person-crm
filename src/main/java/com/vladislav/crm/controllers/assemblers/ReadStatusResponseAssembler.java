package com.vladislav.crm.controllers.assemblers;

import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import com.vladislav.crm.entities.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadStatusResponseAssembler extends RepresentationModelAssembler<Status, EntityModel<ReadStatusResponse>> {
}
