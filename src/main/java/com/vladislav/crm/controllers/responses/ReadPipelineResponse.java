package com.vladislav.crm.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReadPipelineResponse {

    private Long id;
    private String name;
    private Long userId;
    private List<Long> statusesId = new ArrayList<>();

}
