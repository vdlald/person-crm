package com.vladislav.crm.services.operations.statuses;

import com.vladislav.crm.entities.Status;

import java.util.Collection;

public interface ReadPipelineStatusesOperation {
    Collection<Status> execute(Long pipelineId);
}
