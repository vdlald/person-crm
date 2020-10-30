package com.vladislav.crm.communications.web.handlers.statuses;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteStatusRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
