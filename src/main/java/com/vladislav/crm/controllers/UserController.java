package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;

public interface UserController {
    EntityModel<User> currentUser();

    EntityModel<User> createUser(CreateUserRequest request);
}
