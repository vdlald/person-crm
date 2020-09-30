package com.vladislav.crm.services.operations.pipelines;

import com.vladislav.crm.entities.Pipeline;

import java.util.List;

public interface ReadUserPipelinesOperation {
    List<Pipeline> execute(Long userId);
}
