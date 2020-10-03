package com.vladislav.crm.web.handlers.statuses;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.CreateStatusRequest;
import com.vladislav.crm.web.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateStatusRequestHandler extends RequestHandler<CreateStatusRequest, EntityModel<ReadStatusResponse>> {
}
