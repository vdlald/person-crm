package com.vladislav.crm.web.requesthandlers.contacts;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteContactRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
