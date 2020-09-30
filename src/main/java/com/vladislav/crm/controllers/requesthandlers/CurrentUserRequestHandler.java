package com.vladislav.crm.controllers.requesthandlers;

import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;

public interface CurrentUserRequestHandler extends RequestHandler<Void, EntityModel<User>> {

    EntityModel<User> handle();
}
