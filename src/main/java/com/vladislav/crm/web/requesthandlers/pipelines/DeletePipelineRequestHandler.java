package com.vladislav.crm.web.requesthandlers.pipelines;

import com.vladislav.crm.web.requesthandlers.RequestHandler;
import org.springframework.http.ResponseEntity;

public interface DeletePipelineRequestHandler extends RequestHandler<Long, ResponseEntity<Void>> {
}
