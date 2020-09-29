package com.vladislav.crm.controllers.assemblers;

import com.vladislav.crm.controllers.UserContactsController;
import com.vladislav.crm.controllers.responses.CompanyResponse;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.entities.Contact;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReadContactResponseAssemblerImpl implements ReadContactResponseAssembler {
    @Override
    public EntityModel<ReadContactResponse> toModel(Contact entity) {
        final ReadContactResponse response = new ReadContactResponse()
                .setId(entity.getId())
                .setName(entity.getName())
                .setCompany(new CompanyResponse().setName(entity.getCompany().getName()))
                .setUserId(entity.getUser().getId());

        final Link selfRel = linkTo(methodOn(UserContactsController.class)
                .readContact(entity.getId()))
                .withSelfRel();

        return EntityModel.of(response, selfRel);
    }
}
