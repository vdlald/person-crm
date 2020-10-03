package com.vladislav.crm.web.handlers.contacts;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteContactRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
