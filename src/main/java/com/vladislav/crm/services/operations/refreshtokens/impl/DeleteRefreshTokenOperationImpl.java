package com.vladislav.crm.services.operations.refreshtokens.impl;

import com.vladislav.crm.repositories.RefreshTokenRepository;
import com.vladislav.crm.services.operations.refreshtokens.DeleteRefreshTokenOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteRefreshTokenOperationImpl implements DeleteRefreshTokenOperation {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void execute(UUID refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }
}
