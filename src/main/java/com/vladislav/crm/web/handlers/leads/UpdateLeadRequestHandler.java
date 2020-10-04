package com.vladislav.crm.web.handlers.leads;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.UpdateLeadRequest;
import com.vladislav.crm.web.responses.ReadLeadResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateLeadRequestHandler extends RequestHandler<Pair<Long, UpdateLeadRequest>, EntityModel<ReadLeadResponse>> {
}
