package com.vladislav.crm.controllers.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReadStatusResponse {

    // todo: Дополнить поля
    private String name;

}
