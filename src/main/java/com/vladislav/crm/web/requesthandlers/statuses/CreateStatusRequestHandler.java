package com.vladislav.crm.web.requesthandlers.statuses;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import com.vladislav.crm.web.requests.CreateStatusRequest;
import com.vladislav.crm.web.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateStatusRequestHandler extends RequestHandler<CreateStatusRequest, EntityModel<ReadStatusResponse>> {
}
