package com.vladislav.crm.communications.web.adapters.pipelines.impl;

import com.vladislav.crm.communications.handlers.AbstractDeleteEntityRequestHandler;
import com.vladislav.crm.communications.web.adapters.AbstractDeleteEntityRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.pipelines.DeletePipelineRequestHandlerAdapter;
import com.vladislav.crm.entities.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePipelineRequestHandlerAdapterAdapterImpl
        extends AbstractDeleteEntityRequestHandlerAdapter<Pipeline>
        implements DeletePipelineRequestHandlerAdapter {

    @Autowired
    public DeletePipelineRequestHandlerAdapterAdapterImpl(AbstractDeleteEntityRequestHandler<Pipeline> requestHandler) {
        super(requestHandler);
    }
}
