package com.vladislav.crm.communications.web.adapters.companies;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.CreateCompanyRequest;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateCompanyRequestHandler extends RequestHandlerAdapter<CreateCompanyRequest, EntityModel<CompanyResponse>> {
}
