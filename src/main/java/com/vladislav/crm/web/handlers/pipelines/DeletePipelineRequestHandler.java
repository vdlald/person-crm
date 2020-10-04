package com.vladislav.crm.web.handlers.pipelines;

import com.vladislav.crm.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeletePipelineRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
