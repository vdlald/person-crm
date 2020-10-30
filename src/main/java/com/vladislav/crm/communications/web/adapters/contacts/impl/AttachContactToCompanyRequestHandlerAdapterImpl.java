package com.vladislav.crm.communications.web.adapters.contacts.impl;

import com.vladislav.crm.services.operations.contacts.AttachContactToCompanyOperation;
import com.vladislav.crm.communications.web.adapters.contacts.AttachContactToCompanyRequestHandlerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachContactToCompanyRequestHandlerAdapterImpl implements AttachContactToCompanyRequestHandlerAdapter {

    private final AttachContactToCompanyOperation attachContactToCompanyOperation;

    @Override
    public ResponseEntity<Void> handle(Pair<Long, Long> requestPair) {
        attachContactToCompanyOperation.execute(requestPair.getFirst(), requestPair.getSecond());
        return ResponseEntity.noContent().build();
    }
}
