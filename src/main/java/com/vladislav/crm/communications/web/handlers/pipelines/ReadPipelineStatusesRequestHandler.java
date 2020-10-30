package com.vladislav.crm.communications.web.handlers.pipelines;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.hateoas.RepresentationModel;

public interface ReadPipelineStatusesRequestHandler extends RequestHandler<Long, RepresentationModel<?>> {
}
