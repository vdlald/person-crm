package com.vladislav.crm.web.requesthandlers.users;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;

public interface CurrentUserRequestHandler extends RequestHandler<Void, EntityModel<User>> {

    EntityModel<User> handle();
}
