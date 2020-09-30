package com.vladislav.crm.controllers.requesthandlers.contacts;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteContactRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
