package com.vladislav.crm.communications.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreateContactRequest {

    @NotBlank
    @Size(min = 1, max = 32)
    private String name;

    private Long companyId;

    public com.vladislav.crm.communications.requests.CreateContactRequest toCommunicationRequest() {
        return new com.vladislav.crm.communications.requests.CreateContactRequest()
                .setName(name)
                .setCompanyId(companyId);
    }
}
