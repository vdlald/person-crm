package com.vladislav.crm.communications.web.handlers.statuses;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadStatusLeadsRequestHandler extends RequestHandler<Long, RepresentationModel<?>> {
}
