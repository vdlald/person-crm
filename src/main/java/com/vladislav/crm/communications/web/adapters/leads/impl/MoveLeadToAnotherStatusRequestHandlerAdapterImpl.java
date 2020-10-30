package com.vladislav.crm.communications.web.adapters.leads.impl;

import com.vladislav.crm.communications.handlers.leads.MoveLeadToAnotherStatusRequestHandler;
import com.vladislav.crm.communications.web.adapters.leads.MoveLeadToAnotherStatusRequestHandlerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("webMoveLeadToAnotherStatusRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoveLeadToAnotherStatusRequestHandlerAdapterImpl implements MoveLeadToAnotherStatusRequestHandlerAdapter {

    private final MoveLeadToAnotherStatusRequestHandler requestHandler;

    @Override
    public ResponseEntity<Void> handle(Pair<Long, Long> requestPair) {
        requestHandler.handle(requestPair);
        return ResponseEntity.noContent().build();
    }
}
