package com.vladislav.crm.web.handlers.companies;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserCompaniesRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
