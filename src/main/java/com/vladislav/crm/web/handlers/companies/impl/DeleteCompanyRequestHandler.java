package com.vladislav.crm.web.handlers.companies.impl;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeleteCompanyRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
