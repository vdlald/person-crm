package com.vladislav.crm.communications.web.controllers;

import com.vladislav.crm.communications.web.requests.CreateUserRequest;
import com.vladislav.crm.communications.web.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;

public interface UserController {
    EntityModel<GetCurrentUserResponse> currentUser();

    EntityModel<GetCurrentUserResponse> createUser(CreateUserRequest request);

    EntityModel<GetCurrentUserResponse> updateUserInfo(UpdateCurrentUserInfoRequest request);
}
