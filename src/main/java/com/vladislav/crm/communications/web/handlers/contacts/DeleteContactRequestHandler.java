package com.vladislav.crm.communications.web.handlers.contacts;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteContactRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
