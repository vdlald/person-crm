package com.vladislav.crm.communications.web.handlers.leads;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteLeadRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
