package com.vladislav.crm.web.controllers.impl;

import com.vladislav.crm.web.controllers.UserController;
import com.vladislav.crm.web.requesthandlers.users.CreateUserRequestHandler;
import com.vladislav.crm.web.requesthandlers.users.CurrentUserRequestHandler;
import com.vladislav.crm.web.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserControllerImpl implements UserController {

    private final CreateUserRequestHandler createUserRequestHandler;
    private final CurrentUserRequestHandler currentUserRequestHandler;

    @Override
    @GetMapping("/current")
    public EntityModel<User> currentUser() {
        return currentUserRequestHandler.handle();
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        return createUserRequestHandler.handle(request);
    }
}
