package com.devstack.itemserviceapi.dto.request;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestItemDto {

    private String description;

    private long qty;

    private double unitPrice;

}
