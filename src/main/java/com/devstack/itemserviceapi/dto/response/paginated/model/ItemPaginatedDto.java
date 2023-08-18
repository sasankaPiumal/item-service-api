package com.devstack.itemserviceapi.dto.response.paginated.model;


import com.devstack.itemserviceapi.dto.response.ResponseItemDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemPaginatedDto {

    private long dataCount;

    private List<ResponseItemDto> list;
}
