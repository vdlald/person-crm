package com.vladislav.crm.web.assemblers.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.web.assemblers.GetCurrentUserResponseAssembler;
import com.vladislav.crm.web.controllers.impl.UserControllerImpl;
import com.vladislav.crm.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GetCurrentUserResponseAssemblerImpl implements GetCurrentUserResponseAssembler {
    @Override
    public EntityModel<GetCurrentUserResponse> toModel(User entity) {
        final Link selfRel = linkTo(methodOn(UserControllerImpl.class).currentUser()).withSelfRel();

        final UserInfo info = entity.getInfo();
        final GetCurrentUserResponse.UserInfoResponse userInfoResponse = new GetCurrentUserResponse.UserInfoResponse()
                .setEmail(info.getEmail())
                .setFirstname(info.getFirstname())
                .setLastname(info.getLastname());

        final GetCurrentUserResponse response = new GetCurrentUserResponse()
                .setId(entity.getId())
                .setUsername(entity.getUsername())
                .setInfo(userInfoResponse);

        return EntityModel.of(response, selfRel);
    }
}
