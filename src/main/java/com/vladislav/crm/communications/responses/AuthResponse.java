package com.vladislav.crm.communications.responses;

import com.vladislav.crm.entities.RefreshToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AuthResponse {

    private String accessToken;
    private RefreshToken refreshToken;

}
