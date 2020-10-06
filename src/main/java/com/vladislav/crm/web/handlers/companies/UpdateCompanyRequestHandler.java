package com.vladislav.crm.web.handlers.companies;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.UpdateCompanyRequest;
import com.vladislav.crm.web.responses.CompanyResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateCompanyRequestHandler extends RequestHandler<Pair<Long, UpdateCompanyRequest>, EntityModel<CompanyResponse>> {
}
