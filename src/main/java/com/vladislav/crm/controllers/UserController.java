package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.requesthandlers.CreateUserRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.CurrentUserRequestHandler;
import com.vladislav.crm.controllers.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final CreateUserRequestHandler createUserRequestHandler;
    private final CurrentUserRequestHandler currentUserRequestHandler;

    @GetMapping("/current")
    public EntityModel<User> currentUser() {
        return currentUserRequestHandler.handle();
    }

    @PostMapping("/")
    public EntityModel<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        return createUserRequestHandler.handle(request);
    }
}
