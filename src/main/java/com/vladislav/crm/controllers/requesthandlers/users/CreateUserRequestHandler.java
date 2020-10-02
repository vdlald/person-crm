package com.vladislav.crm.controllers.requesthandlers.users;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;

public interface CreateUserRequestHandler extends RequestHandler<CreateUserRequest, EntityModel<User>> {
}
