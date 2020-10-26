package com.vladislav.crm.services;

import com.vladislav.crm.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String generateToken(User user);

    UserDetails parseToken(String token);
}
