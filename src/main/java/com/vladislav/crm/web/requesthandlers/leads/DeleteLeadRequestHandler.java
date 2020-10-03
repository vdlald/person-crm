package com.vladislav.crm.web.requesthandlers.leads;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteLeadRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
