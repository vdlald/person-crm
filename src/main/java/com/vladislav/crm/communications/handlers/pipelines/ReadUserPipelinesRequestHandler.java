package com.vladislav.crm.communications.handlers.pipelines;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.entities.Pipeline;

import java.util.Collection;

public interface ReadUserPipelinesRequestHandler extends RequestHandler<Void, Collection<Pipeline>> {
    Collection<Pipeline> handle();
}
