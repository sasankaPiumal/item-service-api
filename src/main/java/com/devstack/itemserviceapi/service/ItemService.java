package com.devstack.itemserviceapi.service;

import com.devstack.itemserviceapi.dto.request.RequestItemDto;
import com.devstack.itemserviceapi.dto.response.ResponseItemDto;
import com.devstack.itemserviceapi.dto.response.paginated.model.ItemPaginatedDto;

public interface ItemService {
    public ResponseItemDto saveItem(RequestItemDto requestItemDto);

    public ResponseItemDto findItem(long id);

    public ResponseItemDto updateItem(long ids, RequestItemDto requestItemDto);

    public void deleteItem(long id);

    public ItemPaginatedDto getAllItems(int page, int size, String searchText);
}
