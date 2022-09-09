package ru.practicum.shareit.item.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.shareit.handler.exceptions.ItemNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Service
public interface ItemService {

    Item save(ItemDto itemDto, long userId);

    Item put(ItemDto itemDto, long itemId, long userId) throws ItemNotFound;

    void delete(long itemId) throws ItemNotFound;

    List<Item> findAll();

    Item findById(long itemId) throws ItemNotFound;

    List<Item> findAllByUserId(long userId);

    List<Item> findAllByItemName(@RequestParam String text);
}
