package com.vladislav.crm.communications.handlers.leads;

import com.vladislav.crm.communications.handlers.RequestHandler;
import org.springframework.data.util.Pair;

public interface AttachLeadToContactRequestHandler extends RequestHandler<Pair<Long, Long>, Void> {
}
