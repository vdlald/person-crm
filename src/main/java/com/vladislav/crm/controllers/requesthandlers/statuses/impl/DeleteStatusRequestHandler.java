package com.vladislav.crm.controllers.requesthandlers.statuses.impl;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteStatusRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
