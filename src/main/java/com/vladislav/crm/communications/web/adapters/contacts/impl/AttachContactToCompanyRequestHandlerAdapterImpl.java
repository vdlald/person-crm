package com.vladislav.crm.communications.web.adapters.contacts.impl;

import com.vladislav.crm.communications.handlers.contacts.AttachContactToCompanyRequestHandler;
import com.vladislav.crm.communications.web.adapters.contacts.AttachContactToCompanyRequestHandlerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("AttachContactToCompanyRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachContactToCompanyRequestHandlerAdapterImpl implements AttachContactToCompanyRequestHandlerAdapter {

    private final AttachContactToCompanyRequestHandler requestHandler;

    @Override
    public ResponseEntity<Void> handle(Pair<Long, Long> requestPair) {
        requestHandler.handle(requestPair);
        return ResponseEntity.noContent().build();
    }
}
