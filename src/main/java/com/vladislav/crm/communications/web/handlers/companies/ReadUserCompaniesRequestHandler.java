package com.vladislav.crm.communications.web.handlers.companies;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserCompaniesRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
