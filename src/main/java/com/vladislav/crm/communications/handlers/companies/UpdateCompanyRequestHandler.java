package com.vladislav.crm.communications.handlers.companies;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.UpdateCompanyRequest;
import com.vladislav.crm.entities.Company;
import org.springframework.data.util.Pair;

public interface UpdateCompanyRequestHandler extends RequestHandler<Pair<Long, UpdateCompanyRequest>, Company> {
}
