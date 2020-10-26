package com.vladislav.crm.services;

import com.vladislav.crm.entities.User;

public interface TokenService {
    String generateToken(User user);
}
