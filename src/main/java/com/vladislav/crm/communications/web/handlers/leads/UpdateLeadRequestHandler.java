package com.vladislav.crm.communications.web.handlers.leads;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.UpdateLeadRequest;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateLeadRequestHandler extends RequestHandler<Pair<Long, UpdateLeadRequest>, EntityModel<ReadLeadResponse>> {
}
