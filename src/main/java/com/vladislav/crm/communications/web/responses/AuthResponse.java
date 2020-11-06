package com.vladislav.crm.communications.web.responses;

import com.vladislav.crm.entities.RefreshToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AuthResponse {

    private String accessToken;
    private RefreshTokenResponse refreshToken;

    public static AuthResponse of(com.vladislav.crm.communications.responses.AuthResponse authResponse) {
        final RefreshToken refreshToken = authResponse.getRefreshToken();

        final RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse()
                .setToken(refreshToken.getToken())
                .setValidUntil(refreshToken.getValidUntil());

        return new AuthResponse()
                .setAccessToken(authResponse.getAccessToken())
                .setRefreshToken(refreshTokenResponse);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class RefreshTokenResponse {

        private UUID token = UUID.randomUUID();
        private LocalDateTime validUntil;

    }
}
