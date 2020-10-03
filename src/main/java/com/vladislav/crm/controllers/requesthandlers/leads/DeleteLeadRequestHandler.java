package com.vladislav.crm.controllers.requesthandlers.leads;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteLeadRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
