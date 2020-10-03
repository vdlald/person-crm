package com.vladislav.crm.web.requesthandlers.users;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import com.vladislav.crm.web.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;

public interface CreateUserRequestHandler extends RequestHandler<CreateUserRequest, EntityModel<User>> {
}
