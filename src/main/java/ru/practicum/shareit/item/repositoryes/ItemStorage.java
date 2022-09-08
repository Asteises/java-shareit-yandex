package ru.practicum.shareit.item.repositoryes;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.handler.exceptions.ItemNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Repository
public interface ItemStorage {

    Item save(ItemDto itemDto, long userId);

    Item put(ItemDto itemDto, long itemId) throws ItemNotFound;

    void delete(long itemId) throws ItemNotFound;

    List<Item> findAll();

    Item findById(long itemId) throws ItemNotFound;

    List<Item> findAllByUserId(long userId) throws ItemNotFound;
}
