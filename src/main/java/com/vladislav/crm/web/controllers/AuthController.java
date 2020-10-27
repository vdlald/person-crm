package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.handlers.auth.AuthRequestHandler;
import com.vladislav.crm.web.requests.AuthRequest;
import com.vladislav.crm.web.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// todo: introduce interface
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthRequestHandler authRequestHandler;

    @PostMapping("")
    public AuthResponse auth(@RequestBody AuthRequest authRequest) {
        return authRequestHandler.handle(authRequest);
    }
}
