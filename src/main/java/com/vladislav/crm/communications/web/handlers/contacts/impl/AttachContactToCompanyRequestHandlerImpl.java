package com.vladislav.crm.communications.web.handlers.contacts.impl;

import com.vladislav.crm.services.operations.contacts.AttachContactToCompanyOperation;
import com.vladislav.crm.communications.web.handlers.contacts.AttachContactToCompanyRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachContactToCompanyRequestHandlerImpl implements AttachContactToCompanyRequestHandler {

    private final AttachContactToCompanyOperation attachContactToCompanyOperation;

    @Override
    public ResponseEntity<Void> handle(Pair<Long, Long> requestPair) {
        attachContactToCompanyOperation.execute(requestPair.getFirst(), requestPair.getSecond());
        return ResponseEntity.noContent().build();
    }
}
