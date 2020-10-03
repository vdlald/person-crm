package com.vladislav.crm.web.handlers.statuses;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.UpdateStatusRequest;
import com.vladislav.crm.web.responses.ReadStatusResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateStatusRequestHandler extends RequestHandler<Pair<Long, UpdateStatusRequest>, EntityModel<ReadStatusResponse>> {
}
