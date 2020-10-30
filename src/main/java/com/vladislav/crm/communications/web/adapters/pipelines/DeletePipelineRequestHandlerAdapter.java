package com.vladislav.crm.communications.web.adapters.pipelines;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.http.ResponseEntity;

public interface DeletePipelineRequestHandlerAdapter extends RequestHandlerAdapter<Long, ResponseEntity<Void>> {
}
