package com.vladislav.crm.controllers.requesthandlers.impl;

import com.vladislav.crm.controllers.requesthandlers.DeleteContactRequestHandler;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.contacts.DeleteContactOperation;
import com.vladislav.crm.services.operations.contacts.ReadContactOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteContactRequestHandlerImpl implements DeleteContactRequestHandler {

    private final ReadContactOperation readContactOperation;
    private final DeleteContactOperation deleteContactOperation;
    private final GetCurrentUserOperation getCurrentUserOperation;

    @Override
    public ResponseEntity<Void> handle(Long contactId) {
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
}
