package com.vladislav.crm.web.requesthandlers.statuses;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteStatusRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
