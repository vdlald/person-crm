package com.vladislav.crm.services.operations.pipelines;

import com.vladislav.crm.entities.Pipeline;

import java.util.Collection;

public interface ReadUserPipelinesOperation {
    Collection<Pipeline> execute(Long userId);
}
