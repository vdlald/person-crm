package com.vladislav.crm.communications.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateCurrentUserInfoRequest {

    @Size(max = 64)
    @Column(name = "email", unique = true, length = 64)
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @Size(max = 32)
    @Column(name = "firstname", length = 32)
    private String firstname;

    @Size(max = 32)
    @Column(name = "lastname", length = 32)
    private String lastname;

    public com.vladislav.crm.communications.requests.UpdateCurrentUserInfoRequest toCommunicationRequest() {
        return new com.vladislav.crm.communications.requests.UpdateCurrentUserInfoRequest()
                .setEmail(email)
                .setFirstname(firstname)
                .setLastname(lastname);
    }
}
