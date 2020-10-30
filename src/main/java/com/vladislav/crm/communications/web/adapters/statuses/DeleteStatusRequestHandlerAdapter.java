package com.vladislav.crm.communications.web.adapters.statuses;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.http.ResponseEntity;

public interface DeleteStatusRequestHandlerAdapter extends RequestHandlerAdapter<Long, ResponseEntity<Void>> {
}
