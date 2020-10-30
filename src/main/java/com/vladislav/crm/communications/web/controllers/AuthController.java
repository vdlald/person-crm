package com.vladislav.crm.communications.web.controllers;

import com.vladislav.crm.communications.requests.AuthRequest;
import com.vladislav.crm.communications.web.responses.AuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
    @PostMapping("")
    AuthResponse auth(@RequestBody AuthRequest authRequest);
}
