package com.vladislav.crm.communications.handlers.statuses;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.entities.Lead;

import java.util.Collection;

public interface ReadStatusLeadsRequestHandler extends RequestHandler<Long, Collection<Lead>> {
}
