package com.vladislav.crm.communications.web.adapters.pipelines;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.CreatePipelineRequest;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;

public interface CreatePipelineRequestHandler extends RequestHandlerAdapter<CreatePipelineRequest, EntityModel<ReadPipelineResponse>> {
}
