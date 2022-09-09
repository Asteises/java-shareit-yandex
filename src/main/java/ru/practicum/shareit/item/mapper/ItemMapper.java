package ru.practicum.shareit.item.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.handler.exceptions.ItemRequestNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.services.RequestService;

@Service
@RequiredArgsConstructor
public class ItemMapper {

    private final RequestService requestService;

    public ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getRequest() != null ? item.getRequest().getId() : null
        );
    }

    public Item toItem(ItemDto itemDto) throws ItemRequestNotFound {
        Item item = new Item();
        if (requestService.findById(itemDto.getRequest()) != null) {
            item.setRequest(requestService.findById(itemDto.getRequest()));
        } else {
            item.setRequest(null);
        }
        if (itemDto.getName() != null && !itemDto.getName().isEmpty()) {
            item.setName(itemDto.getName());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getDescription() != null) {
            item.setDescription(itemDto.getDescription());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getAvailable() != null) {
            item.setAvailable(itemDto.getAvailable());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return item;
    }
}
