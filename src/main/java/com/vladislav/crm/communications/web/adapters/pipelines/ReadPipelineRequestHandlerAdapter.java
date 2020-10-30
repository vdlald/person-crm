package com.vladislav.crm.communications.web.adapters.pipelines;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadPipelineResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadPipelineRequestHandlerAdapter extends RequestHandlerAdapter<Long, EntityModel<ReadPipelineResponse>> {
}
