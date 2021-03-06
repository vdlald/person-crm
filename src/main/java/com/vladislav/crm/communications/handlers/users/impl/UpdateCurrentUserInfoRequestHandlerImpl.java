package com.vladislav.crm.communications.handlers.users.impl;

import com.vladislav.crm.communications.handlers.users.UpdateCurrentUserInfoRequestHandler;
import com.vladislav.crm.communications.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.services.operations.UpdateOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateCurrentUserInfoRequestHandlerImpl implements UpdateCurrentUserInfoRequestHandler {

    private final GetCurrentUserOperation getCurrentUserOperation;
    private final UpdateOperation<User> userUpdateOperation;

    @Override
    public User handle(UpdateCurrentUserInfoRequest request) {
        final User user = getCurrentUserOperation.execute();
        final UserInfo info = user.getInfo();

        info.setEmail(request.getEmail())
                .setFirstname(request.getFirstname())
                .setLastname(request.getLastname());

        return userUpdateOperation.execute(user);
    }
}
