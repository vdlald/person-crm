package com.vladislav.crm.web.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReadStatusResponse {

    private Long id;
    private String name;
    private Long pipelineId;
    private Statistics statistics;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Statistics {

        private BigDecimal saleTotal;
        private Integer leadTotal;

    }
}
