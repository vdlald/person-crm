package com.vladislav.crm.controllers.requesthandlers.statuses;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.requests.UpdateStatusRequest;
import com.vladislav.crm.controllers.responses.ReadStatusResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateStatusRequestHandler extends RequestHandler<Pair<Long, UpdateStatusRequest>, EntityModel<ReadStatusResponse>> {
}
