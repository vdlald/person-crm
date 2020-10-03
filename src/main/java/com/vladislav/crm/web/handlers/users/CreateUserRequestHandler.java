package com.vladislav.crm.web.handlers.users;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;

public interface CreateUserRequestHandler extends RequestHandler<CreateUserRequest, EntityModel<User>> {
}
