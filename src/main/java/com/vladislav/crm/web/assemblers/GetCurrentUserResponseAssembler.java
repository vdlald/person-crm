package com.vladislav.crm.web.assemblers;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface GetCurrentUserResponseAssembler extends RepresentationModelAssembler<User, EntityModel<GetCurrentUserResponse>> {
}
