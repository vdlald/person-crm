package com.vladislav.crm.communications.web.handlers.companies;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteCompanyRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
