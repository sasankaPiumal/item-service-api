package com.devstack.itemserviceapi.util.mappers;

import com.devstack.itemserviceapi.dto.core.ItemDto;
import com.devstack.itemserviceapi.dto.response.ResponseItemDto;
import com.devstack.itemserviceapi.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItem(ItemDto itemDto);

    ResponseItemDto toResponseItemDto(Item item);

    List<ResponseItemDto> toResponseItemsDtoList(Page<Item> items);
}
