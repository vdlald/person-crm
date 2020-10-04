package com.vladislav.crm.web.handlers.pipelines;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.CreatePipelineRequest;
import com.vladislav.crm.web.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;

public interface CreatePipelineRequestHandler extends RequestHandler<CreatePipelineRequest, EntityModel<ReadPipelineResponse>> {
}
