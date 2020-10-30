package com.vladislav.crm.communications.handlers.pipelines;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.CreatePipelineRequest;
import com.vladislav.crm.entities.Pipeline;

public interface CreatePipelineRequestHandler extends RequestHandler<CreatePipelineRequest, Pipeline> {
}
