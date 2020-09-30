package com.vladislav.crm.services.operations.pipelines;

import com.vladislav.crm.entities.Pipeline;

public interface ReadPipelineOperation {
    Pipeline execute(Long pipelineId);
}
