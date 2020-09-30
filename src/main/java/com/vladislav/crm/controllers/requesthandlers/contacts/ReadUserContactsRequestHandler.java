package com.vladislav.crm.controllers.requesthandlers.contacts;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserContactsRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
