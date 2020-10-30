package com.vladislav.crm.communications.handlers.companies;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.CreateCompanyRequest;
import com.vladislav.crm.entities.Company;

public interface CreateCompanyRequestHandler extends RequestHandler<CreateCompanyRequest, Company> {
}
