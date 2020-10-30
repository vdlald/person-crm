package com.vladislav.crm.communications.handlers.users;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.entities.User;

public interface CurrentUserRequestHandler extends RequestHandler<Void, User> {

    User handle();
}
