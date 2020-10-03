package com.vladislav.crm.web.handlers.leads;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteLeadRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
