package com.vladislav.crm.web.requesthandlers.contacts;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserContactsRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
