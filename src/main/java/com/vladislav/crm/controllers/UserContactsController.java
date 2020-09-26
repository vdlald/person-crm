package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.controllers.assemblers.ReadUserContactsResponseAssembler;
import com.vladislav.crm.controllers.requests.CreateContactRequest;
import com.vladislav.crm.controllers.requests.UpdateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.controllers.responses.ReadUserContactsResponse;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.companies.ReadCompanyOperation;
import com.vladislav.crm.services.operations.contacts.*;
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

    // уже много зависимостей, возможно стоит вынести некоторую логику в отдельный класс
    private final ReadUserContactsResponseAssembler readUserContactsResponseAssembler;
    private final ReadContactResponseAssembler readContactResponseAssembler;

    // refactor candidate: CrudContactsHandler ?
    private final ReadUserContactsOperation readUserContactsOperation;
    private final ReadContactOperation readContactOperation;
    private final CreateContactOperation createContactOperation;
    private final UpdateContactOperation updateContactOperation;
    private final DeleteContactOperation deleteContactOperation;

    private final ReadCompanyOperation readCompanyOperation;

    @GetMapping("/")
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
        if (isUserOwner(user, contact)) {
            return ResponseEntity.ok(readContactResponseAssembler.toModel(contact));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // refactor candidate
    @PostMapping("/")  // вопрос: не слишком ли много кода здесь? может вынести его в отдельный Handler?
    public EntityModel<ReadContactResponse> createContact(
            Authentication authentication,
            @RequestBody CreateContactRequest request
    ) {
        User user = (User) authentication.getPrincipal();

        final Contact contact = new Contact();
        contact.setUser(stubUser(user)).setName(request.getName());

        final CreateContactRequest.CompanyRequest companyRequest = request.getCompany();
        if (companyRequest != null) {
            if (companyRequest.getId() != null) {
                final Company company = readCompanyOperation.execute(companyRequest.getId());
                contact.setCompany(company);
            } else {
                final String companyRequestName = companyRequest.getName();
                if (!companyRequestName.isEmpty() && !companyRequestName.isBlank()) {
                    final Company company = new Company().setName(companyRequestName);
                    contact.setCompany(company);
                }
            }
        }

        return readContactResponseAssembler.toModel(createContactOperation.execute(contact));
    }

    // refactor candidate
    @PutMapping("/{id}")  // вопрос: не слишком ли много кода здесь? может вынести его в отдельный Handler?
    public ResponseEntity<EntityModel<ReadContactResponse>> updateContact(
            Authentication authentication,
            @PathVariable("id") Long contactId,
            @RequestBody UpdateContactRequest request
    ) {
        User user = (User) authentication.getPrincipal();

        final Contact contact = readContactOperation.execute(contactId);
        if (isUserOwner(user, contact)) {
            contact.setName(request.getName());
            final UpdateContactRequest.CompanyRequest companyRequest = request.getCompany();
            if (companyRequest == null) {
                contact.setCompany(null);
            } else {
                final Long companyId = companyRequest.getId();
                final String companyName = companyRequest.getName();
                if (companyId != null) {
                    if (contact.getCompany() == null || !contact.getCompany().getId().equals(companyId)) {
                        contact.setCompany(readCompanyOperation.execute(companyId));
                    }
                } else if (companyName != null) {
                    if (contact.getCompany() == null) {
                        contact.setCompany(new Company().setName(companyName));
                    } else {
                        contact.getCompany().setName(companyName);
                    }
                }
            }

            return ResponseEntity.ok(readContactResponseAssembler.toModel(updateContactOperation.execute(contact)));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(
            Authentication authentication,
            @PathVariable("id") Long contactId
    ) {
        User user = (User) authentication.getPrincipal();

        final Contact contact = readContactOperation.execute(contactId);
        if (isUserOwner(user, contact)) {
            deleteContactOperation.execute(contactId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private boolean isUserOwner(User user, Contact contact) {
        return contact.getUser().getId().equals(user.getId());
    }

    private User stubUser(User user) {
        final User stub = new User();
        stub.setId(user.getId());
        return stub;
    }
}
