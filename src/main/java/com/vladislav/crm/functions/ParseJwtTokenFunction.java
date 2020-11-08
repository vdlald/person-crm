package com.vladislav.crm.functions;

import org.springframework.security.core.userdetails.UserDetails;

public interface ParseJwtTokenFunction {
    UserDetails parseToken(String token);
}
