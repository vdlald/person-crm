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
public class UpdatePipelineRequest {

    @NotBlank
    @Size(min = 1, max = 32)
    private String name;

    public com.vladislav.crm.communications.requests.UpdatePipelineRequest toCommunicationRequest() {
        return new com.vladislav.crm.communications.requests.UpdatePipelineRequest()
                .setName(name);
    }
}
