package com.vladislav.crm.web.controllers;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.TokenService;
import com.vladislav.crm.web.requests.AuthRequest;
import com.vladislav.crm.web.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("")
    public AuthResponse auth(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final User user = (User) userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = tokenService.generateToken(user);
        return new AuthResponse().setToken(token);
    }
}
