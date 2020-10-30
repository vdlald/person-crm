package com.vladislav.crm.communications.web.adapters.leads;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.http.ResponseEntity;

public interface DeleteLeadRequestHandlerAdapter extends RequestHandlerAdapter<Long, ResponseEntity<Void>> {
}
