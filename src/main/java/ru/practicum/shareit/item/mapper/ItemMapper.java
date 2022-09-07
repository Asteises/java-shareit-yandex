package ru.practicum.shareit.item.mapper;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.services.RequestService;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Random;

@Service
public class ItemMapper {

    private RequestService requestService;

    public ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getName(),
                item.getDescription(),
                item.isAvailable(),
                item.getRequest() != null ? item.getRequest().getId() : null
        );
    }

    //TODO Откуда взять owner?
    public Item toItem(ItemDto itemDto) {
        Item item = new Item();
        ItemRequest itemRequest = requestService.findById(itemDto.getRequest());
        item.setId(new Random().nextLong());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.isAvailable());
        item.setRequest(itemRequest);
        return item;
    }
}
