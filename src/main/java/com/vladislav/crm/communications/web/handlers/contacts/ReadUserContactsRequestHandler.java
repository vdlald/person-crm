package com.vladislav.crm.communications.web.handlers.contacts;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserContactsRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
