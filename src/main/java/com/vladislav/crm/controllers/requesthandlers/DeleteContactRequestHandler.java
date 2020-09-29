package com.vladislav.crm.controllers.requesthandlers;

import org.springframework.http.ResponseEntity;

public interface DeleteContactRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
