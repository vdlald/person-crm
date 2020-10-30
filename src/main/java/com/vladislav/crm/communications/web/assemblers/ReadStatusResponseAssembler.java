package com.vladislav.crm.communications.web.assemblers;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadStatusResponseAssembler extends RepresentationModelAssembler<Status, EntityModel<ReadStatusResponse>> {
}
