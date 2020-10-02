package com.vladislav.crm.controllers.requesthandlers.pipelines;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserPipelinesRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
