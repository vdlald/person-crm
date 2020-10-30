package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.UpdateLeadRequest;
import com.vladislav.crm.entities.Lead;
import org.springframework.data.util.Pair;

public interface UpdateLeadRequestHandler extends RequestHandler<Pair<Long, UpdateLeadRequest>, Lead> {
}
