package com.vladislav.crm.controllers.requesthandlers.pipelines;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadPipelineRequestHandler extends RequestHandler<Long, EntityModel<ReadPipelineResponse>> {
}
