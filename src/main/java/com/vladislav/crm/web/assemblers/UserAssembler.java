package com.vladislav.crm.web.assemblers;

import com.vladislav.crm.entities.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface UserAssembler extends RepresentationModelAssembler<User, EntityModel<User>> {
}
