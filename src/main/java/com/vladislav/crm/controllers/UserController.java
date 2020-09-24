package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.assemblers.UserAssembler;
import com.vladislav.crm.controllers.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateUserOperation;
import com.vladislav.crm.services.operations.ReadUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final CreateUserOperation createUserOperation;
    private final ReadUserOperation readUserOperation;
    private final UserAssembler userAssembler;

    @GetMapping("/{id}")
    public EntityModel<User> readUser(@PathVariable Long id) {
        return userAssembler.toModel(readUserOperation.execute(id));
    }

    @PostMapping(value = {"", "/"})
    public EntityModel<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        final User newUser = new User().setUsername(request.getUsername()).setPassword(request.getPassword());
        return userAssembler.toModel(createUserOperation.execute(newUser));
    }
}
