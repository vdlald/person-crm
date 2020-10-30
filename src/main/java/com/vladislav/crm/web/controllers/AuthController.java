package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.AuthRequest;
import com.vladislav.crm.web.responses.AuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
    @PostMapping("")
    AuthResponse auth(@RequestBody AuthRequest authRequest);
}
