package com.vladislav.crm.communications.web.handlers.companies;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.UpdateCompanyRequest;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateCompanyRequestHandler extends RequestHandler<Pair<Long, UpdateCompanyRequest>, EntityModel<CompanyResponse>> {
}
