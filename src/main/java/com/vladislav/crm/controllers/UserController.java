package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.requests.CreateUserRequest;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final CreateUserOperation createUserOperation;

    @PostMapping("/")
    public User createUser(@Valid @RequestBody CreateUserRequest request) {
        final User newUser = new User().setUsername(request.getUsername()).setPassword(request.getPassword());
        return createUserOperation.execute(newUser);
    }
}
