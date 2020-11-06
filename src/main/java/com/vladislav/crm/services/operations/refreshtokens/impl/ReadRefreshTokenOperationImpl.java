package com.vladislav.crm.services.operations.refreshtokens.impl;

import com.vladislav.crm.entities.RefreshToken;
import com.vladislav.crm.repositories.RefreshTokenRepository;
import com.vladislav.crm.services.operations.refreshtokens.ReadRefreshTokenOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadRefreshTokenOperationImpl implements ReadRefreshTokenOperation {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken execute(UUID refreshTokenRaw) {
        return refreshTokenRepository.findById(refreshTokenRaw).orElseThrow(EntityNotFoundException::new);
    }
}
