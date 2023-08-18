package com.devstack.itemserviceapi.api;

import com.devstack.itemserviceapi.dto.request.RequestItemDto;
import com.devstack.itemserviceapi.dto.response.ResponseItemDto;
import com.devstack.itemserviceapi.dto.response.paginated.model.ItemPaginatedDto;
import com.devstack.itemserviceapi.service.ItemService;
import com.devstack.itemserviceapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/items")
public class ItemController {


    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createItem(@RequestBody RequestItemDto requestItemDto) {
        ResponseItemDto savedItem = itemService.saveItem(requestItemDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Successfully saved!", savedItem), HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> findItem(@PathVariable long id) {
        ResponseItemDto responseItemDto = itemService.findItem(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success!", responseItemDto), HttpStatus.OK
        );
    }

    @PutMapping(path = "/{ids}")
    public ResponseEntity<StandardResponse> updateItem(@RequestBody RequestItemDto requestItemDto, @PathVariable long ids) {
        ResponseItemDto updatedItem = itemService.updateItem(ids, requestItemDto);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Successfully updated!", updatedItem), HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> deleteItem(@PathVariable long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successfully Deleted!",null), HttpStatus.OK
        );
    }

    @GetMapping(path = "/all")
    public ResponseEntity<StandardResponse> findAllItems(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String searchText
    ){
        ItemPaginatedDto allItems = itemService.getAllItems(page,size,searchText);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success!",allItems),HttpStatus.OK
        );
    }


}
