package com.vladislav.crm.communications.web.handlers.statuses;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.UpdateStatusRequest;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateStatusRequestHandler extends RequestHandler<Pair<Long, UpdateStatusRequest>, EntityModel<ReadStatusResponse>> {
}
