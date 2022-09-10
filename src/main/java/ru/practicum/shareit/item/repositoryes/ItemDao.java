package ru.practicum.shareit.item.repositoryes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.handler.exceptions.ItemNotFound;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemDao implements ItemStorage {

    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public ItemDto save(Item item) {
        items.put(item.getId(), item);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public ItemDto put(Item item, long itemId) throws ItemNotFound {
        items.replace(itemId, item);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public void delete(long itemId) throws ItemNotFound {
        if (items.containsKey(itemId)) {
            items.remove(itemId, items.get(itemId));
        } else {
            throw new ItemNotFound("Item не найден");
        }
    }

    @Override
    public List<Item> findAll() {
        return items.values().stream().toList();
    }

    @Override
    public Item findById(long itemId) throws ItemNotFound {
        if (items.containsKey(itemId)) {
            return items.get(itemId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Item> findAllByUserId(long userId) throws ItemNotFound, UserNotFound {
        try {
            return items.values().stream()
                    .filter(item -> item.getOwner().getId().equals(userId)).collect(Collectors.toList());
        } catch (UserNotFound e) {
            throw new ItemNotFound("Юзер не найден");
        }
    }

    @Override
    public List<Item> findAllByItemName(String text) {
        List<Item> findItems = new ArrayList<>();
        if (text != null && !text.isEmpty()) {
            Set<Item> buffer = new HashSet<>();
            buffer.addAll(items.values().stream()
                    .filter(item -> item.getName().toLowerCase().contains(text.toLowerCase()))
                    .filter(item -> item.getAvailable().equals(true))
                    .collect(Collectors.toSet()));
            buffer.addAll(items.values().stream()
                    .filter(item -> item.getDescription().toLowerCase().contains(text.toLowerCase()))
                    .filter(item -> item.getAvailable().equals(true))
                    .collect(Collectors.toSet()));
            findItems.addAll(buffer);
        }
        return findItems;
    }
}