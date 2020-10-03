package com.vladislav.crm.web.handlers.pipelines;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadPipelineRequestHandler extends RequestHandler<Long, EntityModel<ReadPipelineResponse>> {
}
