package com.vladislav.crm.communications.web.adapters.companies;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserCompaniesRequestHandler extends RequestHandlerAdapter<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
