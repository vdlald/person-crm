package com.vladislav.crm.communications.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateCurrentUserInfoRequest {

    private String email;
    private String firstname;
    private String lastname;

}
