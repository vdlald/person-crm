package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.web.controllers.UserController;
import com.vladislav.crm.communications.web.adapters.users.CreateUserRequestHandler;
import com.vladislav.crm.communications.web.adapters.users.CurrentUserRequestHandler;
import com.vladislav.crm.communications.web.adapters.users.UpdateCurrentUserInfoRequestHandler;
import com.vladislav.crm.communications.web.requests.CreateUserRequest;
import com.vladislav.crm.communications.web.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
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
    private final UpdateCurrentUserInfoRequestHandler updateCurrentUserInfoRequestHandler;

    @Override
    @GetMapping("/current")
    public EntityModel<GetCurrentUserResponse> currentUser() {
        return currentUserRequestHandler.handle();
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<GetCurrentUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return createUserRequestHandler.handle(request);
    }

    @Override
    @PostMapping("/current/info")
    public EntityModel<GetCurrentUserResponse> updateUserInfo(
            @Valid @RequestBody UpdateCurrentUserInfoRequest request
    ) {
        return updateCurrentUserInfoRequestHandler.handle(request);
    }
}
