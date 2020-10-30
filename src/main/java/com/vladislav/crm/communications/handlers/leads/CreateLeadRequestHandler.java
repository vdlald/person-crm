package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.CreateLeadRequest;
import com.vladislav.crm.entities.Lead;

public interface CreateLeadRequestHandler extends RequestHandler<CreateLeadRequest, Lead> {
}
