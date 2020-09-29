package com.vladislav.crm.controllers.requesthandlers.impl;

import com.vladislav.crm.controllers.assemblers.ReadContactResponseAssembler;
import com.vladislav.crm.controllers.requesthandlers.ReadContactRequestHandler;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.contacts.ReadContactOperation;
import com.vladislav.crm.services.operations.users.GetCurrentUserOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadContactRequestHandlerImpl implements ReadContactRequestHandler {
    
    private final ReadContactResponseAssembler readContactResponseAssembler;
    private final ReadContactOperation readContactOperation;
    private final GetCurrentUserOperation getCurrentUserOperation;
    
    @Override
    public ResponseEntity<EntityModel<ReadContactResponse>> handle(Long contactId) {
        final User user = getCurrentUserOperation.execute();

        final Contact contact = readContactOperation.execute(contactId);
        return ResponseEntity.ok(readContactResponseAssembler.toModel(contact));
//        if (isUserOwner(user, contact)) {
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
    }
    
    private boolean isUserOwner(User user, Contact contact) {
        return contact.getUser().getId().equals(user.getId());
    }
}
