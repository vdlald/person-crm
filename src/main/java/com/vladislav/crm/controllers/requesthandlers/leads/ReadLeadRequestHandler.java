package com.vladislav.crm.controllers.requesthandlers.leads;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.responses.ReadLeadResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadLeadRequestHandler extends RequestHandler<Long, EntityModel<ReadLeadResponse>> {
}
