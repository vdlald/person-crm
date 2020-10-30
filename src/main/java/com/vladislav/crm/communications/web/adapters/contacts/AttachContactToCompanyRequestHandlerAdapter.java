package com.vladislav.crm.communications.web.adapters.contacts;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;

public interface AttachContactToCompanyRequestHandlerAdapter extends RequestHandlerAdapter<Pair<Long, Long>, ResponseEntity<Void>> {
}
