package com.vladislav.crm.communications.web.handlers.statuses;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.CreateStatusRequest;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateStatusRequestHandler extends RequestHandler<CreateStatusRequest, EntityModel<ReadStatusResponse>> {
}
