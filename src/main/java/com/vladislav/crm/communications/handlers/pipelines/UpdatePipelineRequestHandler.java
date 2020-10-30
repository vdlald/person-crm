package com.vladislav.crm.communications.handlers.pipelines;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.UpdatePipelineRequest;
import com.vladislav.crm.entities.Pipeline;
import org.springframework.data.util.Pair;

public interface UpdatePipelineRequestHandler extends RequestHandler<Pair<Long, UpdatePipelineRequest>, Pipeline> {
}
