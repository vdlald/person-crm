package com.vladislav.crm.communications.web.handlers.contacts;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;

public interface AttachContactToCompanyRequestHandler extends RequestHandler<Pair<Long, Long>, ResponseEntity<Void>> {
}
