package com.vladislav.crm.controllers.requesthandlers.pipelines;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeletePipelineRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
