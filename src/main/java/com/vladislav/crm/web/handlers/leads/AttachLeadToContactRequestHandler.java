package com.vladislav.crm.web.handlers.leads;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;

public interface AttachLeadToContactRequestHandler extends RequestHandler<Pair<Long, Long>, ResponseEntity<Void>> {
}
