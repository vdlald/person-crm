package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.requesthandlers.CreateContactRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.ReadContactRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.ReadUserContactsRequestHandler;
import com.vladislav.crm.controllers.requesthandlers.UpdateContactRequestHandler;
import com.vladislav.crm.controllers.requests.CreateContactRequest;
import com.vladislav.crm.controllers.requests.UpdateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.contacts.DeleteContactOperation;
import com.vladislav.crm.services.operations.contacts.ReadContactOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactsController {

    private final ReadContactOperation readContactOperation;
    private final DeleteContactOperation deleteContactOperation;

    private final GetCurrentUserOperation getCurrentUserOperation;

    private final ReadUserContactsRequestHandler readUserContactsRequestHandler;
    private final ReadContactRequestHandler readContactRequestHandler;
    private final CreateContactRequestHandler createContactRequestHandler;
    private final UpdateContactRequestHandler updateContactRequestHandler;

    @GetMapping("/")
    public RepresentationModel<?> readUserContacts() {
        return readUserContactsRequestHandler.handle();
    }

    // вопрос: Domain object security. Стоит ли каждой сущности добавлять поле userId (owner) чтобы иметь возможность легко проверить владение объектом
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ReadContactResponse>> readContact(
            @PathVariable("id") Long contactId
    ) {
        return readContactRequestHandler.handle(contactId);
    }

    @PostMapping("/")
    public EntityModel<ReadContactResponse> createContact(
            @RequestBody CreateContactRequest request
    ) {
        return createContactRequestHandler.handle(request);
    }

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<ReadContactResponse>> updateContact(
            @PathVariable("id") Long contactId,
            @RequestBody UpdateContactRequest request
    ) {
        return updateContactRequestHandler.handle(Pair.of(contactId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(
            @PathVariable("id") Long contactId
    ) {
        final User user = getCurrentUserOperation.execute();

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
