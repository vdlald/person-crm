package com.vladislav.crm.communications.handlers.statuses;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.CreateStatusRequest;
import com.vladislav.crm.entities.Status;

public interface CreateStatusRequestHandler extends RequestHandler<CreateStatusRequest, Status> {
}
