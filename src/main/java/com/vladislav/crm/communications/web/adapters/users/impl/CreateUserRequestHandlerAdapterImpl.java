package com.vladislav.crm.communications.web.adapters.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.CreateOperation;
import com.vladislav.crm.communications.web.assemblers.GetCurrentUserResponseAssembler;
import com.vladislav.crm.communications.web.adapters.users.CreateUserRequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.CreateUserRequest;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserRequestHandlerAdapterImpl implements CreateUserRequestHandlerAdapter {

    private final GetCurrentUserResponseAssembler userAssembler;
    private final CreateOperation<User> createUserOperation;

    @Override
    public EntityModel<GetCurrentUserResponse> handle(CreateUserRequest request) {
        final User newUser = new User()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword());

        final User saved = createUserOperation.execute(newUser);

        return userAssembler.toModel(saved);
    }
}
