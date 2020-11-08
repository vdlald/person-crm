package com.vladislav.crm.functions;

import com.vladislav.crm.entities.RefreshToken;
import com.vladislav.crm.entities.User;

import java.util.function.Function;

public interface GenerateRefreshTokenFunction extends Function<User, RefreshToken> {
}
