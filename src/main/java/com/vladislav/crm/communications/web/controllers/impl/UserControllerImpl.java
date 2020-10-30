package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.web.controllers.UserController;
import com.vladislav.crm.communications.web.adapters.users.CreateUserRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.users.CurrentUserRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.users.UpdateCurrentUserInfoRequestHandlerAdapter;
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

    private final CreateUserRequestHandlerAdapter createUserRequestHandlerAdapter;
    private final CurrentUserRequestHandlerAdapter currentUserRequestHandlerAdapter;
    private final UpdateCurrentUserInfoRequestHandlerAdapter updateCurrentUserInfoRequestHandlerAdapter;

    @Override
    @GetMapping("/current")
    public EntityModel<GetCurrentUserResponse> currentUser() {
        return currentUserRequestHandlerAdapter.handle();
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<GetCurrentUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return createUserRequestHandlerAdapter.handle(request);
    }

    @Override
    @PostMapping("/current/info")
    public EntityModel<GetCurrentUserResponse> updateUserInfo(
            @Valid @RequestBody UpdateCurrentUserInfoRequest request
    ) {
        return updateCurrentUserInfoRequestHandlerAdapter.handle(request);
    }
}
