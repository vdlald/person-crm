package com.vladislav.crm.communications.handlers.auth;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.AuthRequest;
import com.vladislav.crm.communications.responses.AuthResponse;

public interface AuthRequestHandler extends RequestHandler<AuthRequest, AuthResponse> {
}
