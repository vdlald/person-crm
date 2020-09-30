package com.vladislav.crm.controllers.requesthandlers;

import org.springframework.hateoas.RepresentationModel;

public interface ReadUserContactsRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
