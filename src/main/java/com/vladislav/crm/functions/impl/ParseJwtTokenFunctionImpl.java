package com.vladislav.crm.functions.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.ParseJwtTokenFunction;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParseJwtTokenFunctionImpl implements ParseJwtTokenFunction {

    private final JwtParser jwtParser;

    @Override
    @SuppressWarnings("unchecked cast")
    public UserDetails parseToken(String token) {
        final Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        final Claims claims = jws.getBody();

        final User user = new User();

        user.setId(claims.get("userId", Long.class));
        user.setUsername(claims.get("username", String.class));

        final List<String> authorities = (List<String>) claims.get("authorities");
        user.setAuthorities(authorities.stream().map(User.Authority::valueOf).collect(Collectors.toList()));

        return user;
    }
}
