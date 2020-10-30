package com.vladislav.crm.communications.handlers.contacts;

import com.vladislav.crm.communications.handlers.RequestHandler;
import org.springframework.data.util.Pair;

public interface AttachContactToCompanyRequestHandler extends RequestHandler<Pair<Long, Long>, Void> {
}
