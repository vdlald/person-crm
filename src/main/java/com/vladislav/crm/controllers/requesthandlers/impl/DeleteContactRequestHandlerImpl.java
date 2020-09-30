package com.vladislav.crm.controllers.requesthandlers.impl;

import com.vladislav.crm.controllers.requesthandlers.DeleteContactRequestHandler;
import com.vladislav.crm.services.operations.contacts.DeleteContactOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteContactRequestHandlerImpl implements DeleteContactRequestHandler {

    private final DeleteContactOperation deleteContactOperation;

    @Override
    public ResponseEntity<Void> handle(Long contactId) {
        deleteContactOperation.execute(contactId);
        return ResponseEntity.noContent().build();
    }
}
