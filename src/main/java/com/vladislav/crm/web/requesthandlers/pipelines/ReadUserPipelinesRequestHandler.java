package com.vladislav.crm.web.requesthandlers.pipelines;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadUserPipelinesRequestHandler extends RequestHandler<Void, RepresentationModel<?>> {
    RepresentationModel<?> handle();
}
