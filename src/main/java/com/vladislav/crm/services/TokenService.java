package com.vladislav.crm.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    UserDetails parseToken(String token);
}
