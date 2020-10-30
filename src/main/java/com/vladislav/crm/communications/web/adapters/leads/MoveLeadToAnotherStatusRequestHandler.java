package com.vladislav.crm.communications.web.adapters.leads;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;

public interface MoveLeadToAnotherStatusRequestHandler extends RequestHandlerAdapter<Pair<Long, Long>, ResponseEntity<Void>> {
}
