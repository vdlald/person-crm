package com.vladislav.crm.functions;

import com.vladislav.crm.entities.User;

import java.util.function.Function;

public interface GenerateAccessTokenFunction extends Function<User, String> {
}
