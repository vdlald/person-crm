package com.vladislav.crm.communications.handlers.users;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;

public interface CreateUserRequestHandler extends RequestHandler<CreateUserRequest, User> {
}
