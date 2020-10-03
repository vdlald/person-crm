package com.vladislav.crm.controllers.requesthandlers.statuses;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.requests.CreateStatusRequest;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;

public interface CreateStatusRequestHandler extends RequestHandler<CreateStatusRequest, EntityModel<ReadStatusResponse>> {
}
