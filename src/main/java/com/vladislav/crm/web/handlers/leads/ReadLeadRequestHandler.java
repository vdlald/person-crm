package com.vladislav.crm.web.handlers.leads;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadLeadRequestHandler extends RequestHandler<Long, EntityModel<ReadLeadResponse>> {
}
