package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;

public interface UserController {
    EntityModel<User> currentUser();

    EntityModel<User> createUser(CreateUserRequest request);
}
