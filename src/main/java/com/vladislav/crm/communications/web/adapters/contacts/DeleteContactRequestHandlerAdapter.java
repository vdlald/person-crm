package com.vladislav.crm.communications.web.adapters.contacts;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.http.ResponseEntity;

public interface DeleteContactRequestHandlerAdapter extends RequestHandlerAdapter<Long, ResponseEntity<Void>> {
}
