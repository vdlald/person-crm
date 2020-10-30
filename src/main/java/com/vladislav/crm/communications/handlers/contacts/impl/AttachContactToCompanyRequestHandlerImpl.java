package com.vladislav.crm.communications.handlers.contacts.impl;

import com.vladislav.crm.communications.handlers.contacts.AttachContactToCompanyRequestHandler;
import com.vladislav.crm.services.operations.contacts.AttachContactToCompanyOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachContactToCompanyRequestHandlerImpl implements AttachContactToCompanyRequestHandler {

    private final AttachContactToCompanyOperation attachContactToCompanyOperation;

    @Override
    public Void handle(Pair<Long, Long> requestPair) {
        attachContactToCompanyOperation.execute(requestPair.getFirst(), requestPair.getSecond());
        return null;
    }
}
