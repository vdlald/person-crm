package com.vladislav.crm.controllers.assemblers;

import com.vladislav.crm.controllers.UserController;
import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssemblerImpl implements UserAssembler {
    @Override
    public EntityModel<User> toModel(User entity) {
        final Link selfRel = linkTo(methodOn(UserController.class).readUser(null)).withSelfRel();
        return EntityModel.of(entity, selfRel);
    }
}
