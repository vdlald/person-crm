package com.vladislav.crm.communications.web.adapters.companies;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.UpdateCompanyRequest;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateCompanyRequestHandlerAdapter extends RequestHandlerAdapter<Pair<Long, UpdateCompanyRequest>, EntityModel<CompanyResponse>> {
}
