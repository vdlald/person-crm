package com.vladislav.crm.communications.web.handlers.leads;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.CreateLeadRequest;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateLeadRequestHandler extends RequestHandler<CreateLeadRequest, EntityModel<ReadLeadResponse>> {
}
