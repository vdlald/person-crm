package com.vladislav.crm.services.operations.refreshtokens;

import com.vladislav.crm.entities.RefreshToken;

import java.util.UUID;

public interface DeleteRefreshTokenOperation {
    void execute(UUID refreshToken);

    default void execute(RefreshToken refreshToken) {
        execute(refreshToken.getToken());
    }
}
