package com.vladislav.crm.communications.web.assemblers;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface GetCurrentUserResponseAssembler extends RepresentationModelAssembler<User, EntityModel<GetCurrentUserResponse>> {
}
