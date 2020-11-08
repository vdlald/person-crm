package com.vladislav.crm.functions;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface ParseJwtTokenFunction extends Function<String, UserDetails> {
}
