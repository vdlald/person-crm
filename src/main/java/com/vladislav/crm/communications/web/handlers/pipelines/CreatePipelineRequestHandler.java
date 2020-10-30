package com.vladislav.crm.communications.web.handlers.pipelines;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.CreatePipelineRequest;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;

public interface CreatePipelineRequestHandler extends RequestHandler<CreatePipelineRequest, EntityModel<ReadPipelineResponse>> {
}
