package com.vladislav.crm.communications.web.handlers.pipelines;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadPipelineRequestHandler extends RequestHandler<Long, EntityModel<ReadPipelineResponse>> {
}
