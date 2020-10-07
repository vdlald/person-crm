package com.vladislav.crm.web.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GetCurrentUserResponse {

    private Long id;
    private String username;
    private UserInfoResponse info;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class UserInfoResponse {

        private String email;
        private String firstname;
        private String lastname;

    }
}
