package com.vladislav.crm.web.handlers.statuses;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadStatusLeadsRequestHandler extends RequestHandler<Long, RepresentationModel<?>> {
}
