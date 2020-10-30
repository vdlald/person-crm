package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.communications.handlers.leads.AttachLeadToContactRequestHandler;
import com.vladislav.crm.communications.web.adapters.leads.AttachLeadToContactRequestHandlerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("webAttachLeadToContactRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachLeadToContactRequestHandlerAdapterImpl implements AttachLeadToContactRequestHandlerAdapter {

    private final AttachLeadToContactRequestHandler attachLeadToContactRequestHandler;

    @Override
    public ResponseEntity<Void> handle(Pair<Long, Long> requestPair) {
        attachLeadToContactRequestHandler.handle(requestPair);
        return ResponseEntity.noContent().build();
    }
}
