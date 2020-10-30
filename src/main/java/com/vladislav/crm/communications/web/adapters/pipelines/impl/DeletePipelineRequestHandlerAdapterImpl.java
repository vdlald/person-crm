package com.vladislav.crm.communications.web.adapters.pipelines.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.pipelines.DeletePipelineRequestHandlerAdapter;
import com.vladislav.crm.entities.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("webDeletePipelineRequestHandlerAdapter")
public class DeletePipelineRequestHandlerAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Pipeline>
        implements DeletePipelineRequestHandlerAdapter {

    @Autowired
    public DeletePipelineRequestHandlerAdapterImpl(AbstractDeleteEntityRequestHandler<Pipeline> requestHandler) {
        super(requestHandler);
    }
}
