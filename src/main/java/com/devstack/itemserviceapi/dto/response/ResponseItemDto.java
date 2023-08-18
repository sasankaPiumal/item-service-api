package com.devstack.itemserviceapi.dto.response;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItemDto {

    private long ItemId;

    private long code;

    private String description;

    private long qty;

    private double unitPrice;

    private boolean activeState;
}
