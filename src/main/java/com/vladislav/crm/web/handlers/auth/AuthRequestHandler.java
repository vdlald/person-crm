package com.vladislav.crm.web.handlers.auth;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.AuthRequest;
import com.vladislav.crm.web.responses.AuthResponse;

public interface AuthRequestHandler extends RequestHandler<AuthRequest, AuthResponse> {
}
