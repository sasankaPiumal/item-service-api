package com.devstack.itemserviceapi.service.impl;

import com.devstack.itemserviceapi.dto.core.ItemDto;
import com.devstack.itemserviceapi.dto.request.RequestItemDto;
import com.devstack.itemserviceapi.dto.response.ResponseItemDto;
import com.devstack.itemserviceapi.dto.response.paginated.model.ItemPaginatedDto;
import com.devstack.itemserviceapi.entity.Item;
import com.devstack.itemserviceapi.exception.ClassNotFoundException;
import com.devstack.itemserviceapi.exception.EntryNotFoundException;
import com.devstack.itemserviceapi.repo.ItemRepo;
import com.devstack.itemserviceapi.service.ItemService;
import com.devstack.itemserviceapi.util.mappers.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    private final ItemRepo itemRepo;

    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper, ItemRepo itemRepo) {
        this.itemMapper = itemMapper;
        this.itemRepo = itemRepo;
    }

    @Override
    public ResponseItemDto saveItem(RequestItemDto requestItemDto) {
        ItemDto itemDto1 = new ItemDto(
                new Random().nextInt(10001),
                new Random().nextInt(10001),
                requestItemDto.getDescription(),
                requestItemDto.getQty(),
                requestItemDto.getUnitPrice(),
                true
        );

        Item item = itemMapper.toItem(itemDto1);
        itemRepo.save(item);

        return itemMapper.toResponseItemDto(item);
    }

    @Override
    public ResponseItemDto findItem(long id) {
        Optional<Item> selectedItem = itemRepo.findByCode(id);
        if (selectedItem.isPresent()) {
            return itemMapper.toResponseItemDto(selectedItem.get());
        }
        throw new ClassNotFoundException("Item not found with id " + id);
    }

    @Override
    public ResponseItemDto updateItem(long ids, RequestItemDto requestItemDto) {
        Optional<Item> selectedItem = itemRepo.findByCode(ids);
        if (selectedItem.isPresent()) {
            selectedItem.get().setDescription(requestItemDto.getDescription());
            selectedItem.get().setQty(requestItemDto.getQty());
            selectedItem.get().setUnitPrice(requestItemDto.getUnitPrice());

            itemRepo.save(selectedItem.get());
            return itemMapper.toResponseItemDto(selectedItem.get());
        }
        throw new EntryNotFoundException("Not Found!");
    }

    @Override
    public void deleteItem(long id) {
        itemRepo.deleteByCode(id);

    }

    @Override
    public ItemPaginatedDto getAllItems(int page, int size, String searchText) {
        Page<Item> items = itemRepo.searchItemByDescriptionOrUnitPrice(searchText, PageRequest.of(page, size));
        List<ResponseItemDto> responseItemDtoList = itemMapper.toResponseItemsDtoList(items);
        long itemsCount = itemRepo.countBySearchText(searchText);
        for (Item i : items) {
            responseItemDtoList.add(new ResponseItemDto(
                    i.getItemId(),
                    i.getCode(),
                    i.getDescription(),
                    i.getQty(),
                    i.getUnitPrice(),
                    i.isActiveState()
            ));
        }
        return new ItemPaginatedDto(itemsCount,responseItemDtoList);
    }


}
