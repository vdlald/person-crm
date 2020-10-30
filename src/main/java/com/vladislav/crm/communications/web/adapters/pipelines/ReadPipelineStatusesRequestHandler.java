package com.vladislav.crm.communications.web.adapters.pipelines;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.hateoas.RepresentationModel;

public interface ReadPipelineStatusesRequestHandler extends RequestHandlerAdapter<Long, RepresentationModel<?>> {
}
