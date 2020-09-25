package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.controllers.assemblers.ReadUserContactsResponseAssembler;
import com.vladislav.crm.controllers.requests.CreateContactRequest;
import com.vladislav.crm.controllers.requests.UpdateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.controllers.responses.ReadUserContactsResponse;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactsController {

    private final ReadUserContactsResponseAssembler readUserContactsResponseAssembler;
    private final ReadContactResponseAssembler readContactResponseAssembler;

    private final ReadUserContactsOperation readUserContactsOperation;
    private final ReadContactOperation readContactOperation;
    private final CreateContactOperation createContactOperation;
    private final UpdateContactOperation updateContactOperation;
    private final DeleteContactOperation deleteContactOperation;

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

    // вопрос: Domain object security. Стоит ли каждой сущности добавлять поле userId (owner) чтобы иметь возможность легко проверить владение объектом
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ReadContactResponse>> readContact(
            Authentication authentication,
            @PathVariable("id") Long contactId
    ) {
        User user = (User) authentication.getPrincipal();

        final Contact contact = readContactOperation.execute(contactId);
        if (contact.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(readContactResponseAssembler.toModel(contact));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/")
    public EntityModel<ReadContactResponse> createContact(
            Authentication authentication,
            @RequestBody CreateContactRequest request
    ) {
        User user = (User) authentication.getPrincipal();

        return readContactResponseAssembler.toModel(
                createContactOperation.execute(new Contact().setName(request.getName()).setUser(stubUser(user))));
    }

    @PostMapping("/{id}")
    public EntityModel<ReadContactResponse> updateContact(
            Authentication authentication,
            @PathVariable("id") Long contactId,
            @RequestBody UpdateContactRequest request
    ) {
        User user = (User) authentication.getPrincipal();

        return readContactResponseAssembler.toModel(
                updateContactOperation.execute(
                        new Contact().setName(request.getName()).setUser(stubUser(user))));
    }

    @DeleteMapping("/{id}")
    public void deleteContact(
            Authentication authentication,
            @PathVariable("id") Long contactId
    ) {
        deleteContactOperation.execute(contactId);
    }

    private User stubUser(User user) {
        final User stub = new User();
        stub.setId(user.getId());
        return stub;
    }
}
