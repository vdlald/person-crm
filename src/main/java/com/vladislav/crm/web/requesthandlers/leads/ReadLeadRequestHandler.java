package com.vladislav.crm.web.requesthandlers.leads;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadLeadRequestHandler extends RequestHandler<Long, EntityModel<ReadLeadResponse>> {
}
