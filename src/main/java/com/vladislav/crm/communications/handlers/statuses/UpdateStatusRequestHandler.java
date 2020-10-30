package com.vladislav.crm.communications.handlers.statuses;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.UpdateStatusRequest;
import com.vladislav.crm.entities.Status;
import org.springframework.data.util.Pair;

public interface UpdateStatusRequestHandler extends RequestHandler<Pair<Long, UpdateStatusRequest>, Status> {
}
