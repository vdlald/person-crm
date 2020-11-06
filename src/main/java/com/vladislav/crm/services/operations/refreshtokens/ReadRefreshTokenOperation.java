package com.vladislav.crm.services.operations.refreshtokens;

import com.vladislav.crm.entities.RefreshToken;

import java.util.UUID;

public interface ReadRefreshTokenOperation {
    RefreshToken execute(UUID refreshTokenRaw);
}
