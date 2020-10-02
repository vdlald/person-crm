package com.vladislav.crm.controllers.requesthandlers.pipelines;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.requests.UpdatePipelineRequest;
import com.vladislav.crm.controllers.responses.ReadPipelineResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdatePipelineRequestHandler extends RequestHandler<Pair<Long, UpdatePipelineRequest>, EntityModel<ReadPipelineResponse>> {
}
