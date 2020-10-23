package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.CreateUserRequest;
import com.vladislav.crm.web.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;

public interface UserController {
    EntityModel<GetCurrentUserResponse> currentUser();

    EntityModel<GetCurrentUserResponse> createUser(CreateUserRequest request);

    EntityModel<GetCurrentUserResponse> updateUserInfo(UpdateCurrentUserInfoRequest request);
}
