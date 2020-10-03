package com.vladislav.crm.web.handlers.contacts;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserContactsRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
