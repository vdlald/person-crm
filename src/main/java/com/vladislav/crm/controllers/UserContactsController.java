package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.controllers.assemblers.ReadUserContactsResponseAssembler;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.controllers.responses.ReadUserContactsResponse;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.ReadContactOperation;
import com.vladislav.crm.services.operations.ReadUserContactsOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactsController {

    private final ReadUserContactsOperation readUserContactsOperation;
    private final ReadUserContactsResponseAssembler readUserContactsResponseAssembler;

    private final ReadContactOperation readContactOperation;
    private final ReadContactResponseAssembler readContactResponseAssembler;

    @GetMapping(value = {"", "/"})  // вопрос: спросить нормально ли так делать?
    public RepresentationModel<?> readUserContacts(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        final List<EntityModel<ReadUserContactsResponse>> models = readUserContactsOperation.execute(user.getId())
                .stream()
                .map(readUserContactsResponseAssembler::toModel)
                .collect(Collectors.toUnmodifiableList());
        
        return HalModelBuilder.emptyHalModel()
                .embed(models, LinkRelation.of("contacts"))
                .link(linkTo(methodOn(UserContactsController.class).readUserContacts(authentication)).withSelfRel())
                .build();
    }

    @GetMapping("/{id}")  // вопрос: может здесь можно перенести проверку в AOP ?
    public ResponseEntity<EntityModel<ReadContactResponse>> readContact(Authentication authentication, @PathVariable("id") Long contactId) {
        User user = (User) authentication.getPrincipal();

        final Contact contact = readContactOperation.execute(contactId);
        if (contact.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(readContactResponseAssembler.toModel(contact));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
