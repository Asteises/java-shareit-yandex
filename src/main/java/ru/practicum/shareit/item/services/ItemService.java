package ru.practicum.shareit.item.services;

import ru.practicum.shareit.handler.exceptions.ItemNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {

    Item save(ItemDto itemDto);

    Item put(ItemDto itemDto, long itemId) throws ItemNotFound;

    void delete(long itemId) throws ItemNotFound;

    List<Item> findAll();

    Item findById(long itemId) throws ItemNotFound;

    List<Item> findAllByUserId(long userId);
}
