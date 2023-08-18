package com.devstack.itemserviceapi.dto.core;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private long ItemId;

    private long code;

    private String description;

    private long qty;

    private double unitPrice;

    private boolean activeState;
}
