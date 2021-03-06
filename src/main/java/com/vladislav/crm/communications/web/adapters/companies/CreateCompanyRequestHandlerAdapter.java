package com.vladislav.crm.communications.web.adapters.companies;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.CreateCompanyRequest;
import com.vladislav.crm.communications.web.responses.ReadCompanyResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateCompanyRequestHandlerAdapter extends RequestHandlerAdapter<CreateCompanyRequest, EntityModel<ReadCompanyResponse>> {
}
