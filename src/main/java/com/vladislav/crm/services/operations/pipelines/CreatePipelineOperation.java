package com.vladislav.crm.services.operations.pipelines;

import com.vladislav.crm.entities.Pipeline;

public interface CreatePipelineOperation {
    Pipeline execute(Pipeline pipeline);
}
