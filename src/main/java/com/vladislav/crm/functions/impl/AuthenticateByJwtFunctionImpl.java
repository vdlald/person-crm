package com.vladislav.crm.functions.impl;

import com.vladislav.crm.functions.AuthenticateByJwtFunction;
import com.vladislav.crm.functions.ParseJwtTokenFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticateByJwtFunctionImpl implements AuthenticateByJwtFunction {

    private final ParseJwtTokenFunction parseJwtTokenFunction;

    @Override
    public Boolean apply(String requestTokenHeader) {
        if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            final String token = requestTokenHeader.substring(7);

            final UserDetails user = parseJwtTokenFunction.apply(token);

            final UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
            return true;
        }
        return false;
    }
}
