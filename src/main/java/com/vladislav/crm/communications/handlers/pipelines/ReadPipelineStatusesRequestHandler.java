package com.vladislav.crm.communications.handlers.pipelines;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.entities.Status;

import java.util.Collection;

public interface ReadPipelineStatusesRequestHandler extends RequestHandler<Long, Collection<Status>> {
}
