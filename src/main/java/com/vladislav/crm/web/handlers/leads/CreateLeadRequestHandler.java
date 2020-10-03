package com.vladislav.crm.web.handlers.leads;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.CreateLeadRequest;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateLeadRequestHandler extends RequestHandler<CreateLeadRequest, EntityModel<ReadLeadResponse>> {
}
