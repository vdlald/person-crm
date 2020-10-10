package com.vladislav.crm.web.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReadStatusResponse {

    private Long id;
    private String name;
    private Long pipelineId;

}
