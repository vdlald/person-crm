package com.vladislav.crm.communications.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreateStatusRequest {

    @NotBlank
    @Size(min = 1, max = 32)
    private String name;

    @NotNull
    private Long pipelineId;

    public com.vladislav.crm.communications.requests.CreateStatusRequest toCommunicationRequest() {
        return new com.vladislav.crm.communications.requests.CreateStatusRequest()
                .setName(name)
                .setPipelineId(pipelineId);
    }
}
