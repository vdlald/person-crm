package com.vladislav.crm.communications.web.handlers.companies;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.CreateCompanyRequest;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateCompanyRequestHandler extends RequestHandler<CreateCompanyRequest, EntityModel<CompanyResponse>> {
}
