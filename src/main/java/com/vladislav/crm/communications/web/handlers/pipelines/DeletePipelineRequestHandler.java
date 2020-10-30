package com.vladislav.crm.communications.web.handlers.pipelines;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeletePipelineRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
