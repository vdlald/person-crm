package com.vladislav.crm.web.handlers.pipelines;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserPipelinesRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
