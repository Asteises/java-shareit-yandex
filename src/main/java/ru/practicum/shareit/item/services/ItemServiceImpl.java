package ru.practicum.shareit.item.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.handler.exceptions.ItemNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repositoryes.ItemStorage;

import java.util.List;

@Service
@RequiredArgsConstructor // Создает конструктор из тех полей которые нужны
public class ItemServiceImpl implements ItemService {

    private final ItemStorage itemStorage; // Если стоит final для неинициализированного поля то конструктор нужен обязательно

    @Override
    public Item save(ItemDto itemDto, long userId) {
        return itemStorage.save(itemDto, userId);
    }

    @Override
    public Item put(ItemDto itemDto, long itemId, long userId) throws ItemNotFound {
        return itemStorage.put(itemDto, itemId, userId);
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

    @Override
    public List<Item> findAllByItemName(String text) {
        return itemStorage.findAllByItemName(text);
    }
}
