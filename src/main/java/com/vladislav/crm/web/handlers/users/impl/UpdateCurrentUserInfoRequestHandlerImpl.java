package com.vladislav.crm.web.handlers.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.services.operations.UpdateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import com.vladislav.crm.web.assemblers.GetCurrentUserResponseAssembler;
import com.vladislav.crm.web.handlers.users.UpdateCurrentUserInfoRequestHandler;
import com.vladislav.crm.web.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.web.responses.GetCurrentUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateCurrentUserInfoRequestHandlerImpl implements UpdateCurrentUserInfoRequestHandler {

    private final GetCurrentUserOperation getCurrentUserOperation;
    private final UpdateOperation<User> userUpdateOperation;
    private final GetCurrentUserResponseAssembler getCurrentUserResponseAssembler;

    @Override
    public EntityModel<GetCurrentUserResponse> handle(UpdateCurrentUserInfoRequest request) {
        final User user = getCurrentUserOperation.execute();
        final UserInfo info = user.getInfo();

        info.setEmail(request.getEmail())
                .setFirstname(request.getFirstname())
                .setLastname(request.getLastname());

        return getCurrentUserResponseAssembler.toModel(userUpdateOperation.execute(user));
    }
}
