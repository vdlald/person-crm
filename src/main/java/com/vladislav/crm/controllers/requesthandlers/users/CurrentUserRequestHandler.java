package com.vladislav.crm.controllers.requesthandlers.users;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;

public interface CurrentUserRequestHandler extends RequestHandler<Void, EntityModel<User>> {

    EntityModel<User> handle();
}
