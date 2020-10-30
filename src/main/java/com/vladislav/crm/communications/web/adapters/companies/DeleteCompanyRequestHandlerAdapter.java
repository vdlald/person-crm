package com.vladislav.crm.communications.web.adapters.companies;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import org.springframework.http.ResponseEntity;

public interface DeleteCompanyRequestHandlerAdapter extends RequestHandlerAdapter<Long, ResponseEntity<Void>> {
}
