package com.vladislav.crm.controllers.requesthandlers.statuses;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadStatusRequestHandler extends RequestHandler<Long, EntityModel<ReadStatusResponse>> {
}
