package ru.practicum.shareit.item.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.handler.exceptions.ItemNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repositoryes.ItemStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemStorage itemStorage;

    @Override
    public Item save(ItemDto itemDto, long userId) {
        return itemStorage.save(itemDto, userId);
    }

    @Override
    public Item put(ItemDto itemDto, long itemId) throws ItemNotFound {
        return itemStorage.put(itemDto, itemId);
    }

    @Override
    public void delete(long itemId) throws ItemNotFound {
        itemStorage.delete(itemId);
    }

    @Override
    public List<Item> findAll() {
        return itemStorage.findAll();
    }

    @Override
    public Item findById(long itemId) throws ItemNotFound {
        return itemStorage.findById(itemId);
    }

    @Override
    public List<Item> findAllByUserId(long userId) {
        return itemStorage.findAllByUserId(userId);
    }
}
