package com.vladislav.crm.web.handlers.statuses;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteStatusRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
