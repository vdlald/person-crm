package com.vladislav.crm.web.handlers.companies;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.responses.CompanyResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadCompanyRequestHandler extends RequestHandler<Long, EntityModel<CompanyResponse>> {
}
