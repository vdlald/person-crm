package com.vladislav.crm.communications.web.handlers.auth;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.AuthRequest;
import com.vladislav.crm.communications.web.responses.AuthResponse;

public interface AuthRequestHandler extends RequestHandler<AuthRequest, AuthResponse> {
}
