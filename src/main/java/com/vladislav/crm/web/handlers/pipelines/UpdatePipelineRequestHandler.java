package com.vladislav.crm.web.handlers.pipelines;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.UpdatePipelineRequest;
import com.vladislav.crm.web.responses.ReadPipelineResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdatePipelineRequestHandler extends RequestHandler<Pair<Long, UpdatePipelineRequest>, EntityModel<ReadPipelineResponse>> {
}
