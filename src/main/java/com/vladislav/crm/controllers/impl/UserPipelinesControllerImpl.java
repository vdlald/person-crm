package com.vladislav.crm.controllers.impl;

import com.vladislav.crm.controllers.UserPipelinesController;
import com.vladislav.crm.entities.Pipeline;
import com.vladislav.crm.services.operations.pipelines.ReadPipelineOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pipelines")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserPipelinesControllerImpl implements UserPipelinesController {

    private final ReadPipelineOperation readPipelineOperation;

    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsPipelineAuthorization.hasAuthorization(#pipelineId)")
    public Pipeline readPipeline(@PathVariable("id") Long pipelineId) {
        return readPipelineOperation.execute(pipelineId);
    }
}
