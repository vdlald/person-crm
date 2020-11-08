package com.vladislav.crm.services.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenServiceImpl implements TokenService {

    private final JwtParser jwtParser;

    @Override
    public UserDetails parseToken(String token) {
        final Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        final Claims claims = jws.getBody();

        final User user = new User();
        user.setId(claims.get("userId", Long.class));
        user.setUsername(claims.get("username", String.class));
        user.setAuthorities((List<User.Authority>) claims.get("authorities"));

        return user;
    }
}
