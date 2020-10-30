package com.vladislav.crm.communications.web.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReadLeadResponse {

    private Long id;
    private String name;
    private BigDecimal sale;
    private Long userId;
    private Long statusId;
    private List<Long> contactsId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
